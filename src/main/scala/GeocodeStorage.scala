// Copyright 2012 Foursquare Labs Inc. All Rights Reserved.
package com.foursquare.geocoder

import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection

case class DisplayName(
  @Key("l") lang: String,
  @Key("n") name: String,
  @Key("p") preferred: Boolean
)

class DisplayNameOrdering(lang: Option[String], preferAbbrev: Boolean) extends Ordering[DisplayName] {
  def compare(a: DisplayName, b: DisplayName) = {
    scoreName(b) - scoreName(a)
  }

  def scoreName(name: DisplayName): Int = {
    var score = 0
    if (name.preferred) {
      score += 1
    }
    if (lang.exists(_ == name.lang)) {
      score += 2
    }
    if (name.lang == "abbr" && preferAbbrev) {
      score += 4
    }
    score
  }
}

case class FeatureId(
  @Key("n") namespace: String,
  id: String) {
  override def toString = "%s:%s".format(namespace, id)
}

object Implicits {
  implicit def fidToString(fid: FeatureId): String = fid.toString
  implicit def fidListToString(fids: List[FeatureId]): List[String] = fids.map(_.toString)
}

case class Point(lat: Double, lng: Double)

case class BoundingBox(
  ne: Point,
  sw: Point
)

case class GeocodeRecord(
  _id: ObjectId = new ObjectId,
  ids: List[String],
  names: List[String],
  cc: String,
  @Key("wt") _woeType: Int,
  lat: Double,
  lng: Double,
  @Key("dns") displayNames: List[DisplayName],
  @Key("p") parents: List[String],
  population: Option[Int],
  boost: Option[Int] = None,
  @Key("bb") boundingbox: Option[BoundingBox] = None
) extends Ordered[GeocodeRecord] {
  lazy val woeType = YahooWoeType.findByValue(_woeType)
  
  def compare(that: GeocodeRecord): Int = {
    YahooWoeTypes.getOrdering(this.woeType) - YahooWoeTypes.getOrdering(that.woeType)
  }

  def bestName(lang: Option[String], preferAbbrev: Boolean): Option[DisplayName] = {
    displayNames.sorted(new DisplayNameOrdering(lang, preferAbbrev)).headOption
  }

  def toGeocodeFeature(parentMap: Map[String, GeocodeRecord],
      lang: Option[String] = None): GeocodeFeature = {
    val myBestName = bestName(lang, false)

    val parentFeatures = parents.flatMap(pid => parentMap.get(pid)).filterNot(_.isCountry).sorted

    val displayName = (List(myBestName) ++ parentFeatures.map(_.bestName(lang, true)))
      .flatMap(_.map(_.name)).mkString(", ")

    val feature = new GeocodeFeature(
      new GeocodePoint(lat, lng),
      cc
    )

    feature.setName(myBestName.map(_.name).getOrElse(""))
    feature.setDisplayName(displayName)

    boundingbox.foreach(bounds =>
      feature.setBounds(new GeocodeBoundingBox(
        new GeocodePoint(bounds.ne.lat, bounds.ne.lng),
        new GeocodePoint(bounds.sw.lat, bounds.sw.lng)
      ))  
    )

    feature
  }

  def isCountry = woeType == YahooWoeType.COUNTRY
  def isPostalCode = woeType == YahooWoeType.POSTAL_CODE
}

trait GeocodeStorageReadService {
  def getByName(name: String): Iterator[GeocodeRecord]
  def getByIds(ids: Seq[String]): Iterator[GeocodeRecord]
  def getById(id: FeatureId): Iterator[GeocodeRecord]
}

trait GeocodeStorageWriteService {
  def insert(record: GeocodeRecord): Unit
  def setRecordNames(id: FeatureId, names: List[DisplayName])
  def addNameToRecord(name: DisplayName, id: FeatureId)
  def addBoundingBoxToRecord(id: FeatureId, bbox: BoundingBox)
}

trait GeocodeStorageReadWriteService extends GeocodeStorageWriteService with GeocodeStorageReadService

object MongoGeocodeDAO extends SalatDAO[GeocodeRecord, ObjectId](
  collection = MongoConnection()("geocoder")("features"))

class MongoGeocodeStorageService extends GeocodeStorageReadWriteService {
  override def getByName(name: String): Iterator[GeocodeRecord] = {
    MongoGeocodeDAO.find(MongoDBObject("names" -> name))
  }

  def getById(id: FeatureId): Iterator[GeocodeRecord] = {
    MongoGeocodeDAO.find(MongoDBObject("ids" -> MongoDBObject("$in" -> List(id.toString))))
  }

  def getByIds(ids: Seq[String]): Iterator[GeocodeRecord] = {
    MongoGeocodeDAO.find(MongoDBObject("ids" -> MongoDBObject("$in" -> ids)))
  }

  def insert(record: GeocodeRecord) {
    MongoGeocodeDAO.insert(record)
  }

  def addNameToRecord(name: DisplayName, id: FeatureId) {
    MongoGeocodeDAO.update(MongoDBObject("ids" -> MongoDBObject("$in" -> List(id.toString))),
      MongoDBObject("$addToSet" -> MongoDBObject("dns" -> grater[DisplayName].asDBObject(name))),
      false, false)
  }

  def addBoundingBoxToRecord(id: FeatureId, bbox: BoundingBox) {
    MongoGeocodeDAO.update(MongoDBObject("ids" -> MongoDBObject("$in" -> List(id.toString))),
      MongoDBObject("$set" -> MongoDBObject("bb" -> grater[BoundingBox].asDBObject(bbox))),
      false, false)
  }

  def setRecordNames(id: FeatureId, names: List[DisplayName]) {
    MongoGeocodeDAO.update(MongoDBObject("ids" -> MongoDBObject("$in" -> List(id.toString))),
      MongoDBObject("$set" -> MongoDBObject(
        "dns" -> names.map(n => grater[DisplayName].asDBObject(n)))),
      false, false)
  }
}