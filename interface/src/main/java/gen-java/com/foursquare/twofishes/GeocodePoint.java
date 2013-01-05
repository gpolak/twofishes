/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.foursquare.twofishes;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.async.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.protocol.*;

// No additional import required for struct/union.

public class GeocodePoint implements TBase<GeocodePoint, GeocodePoint._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("GeocodePoint");

  private static final TField LAT_FIELD_DESC = new TField("lat", TType.DOUBLE, (short)1);
  private static final TField LNG_FIELD_DESC = new TField("lng", TType.DOUBLE, (short)2);

  public double lat;
  public double lng;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    LAT((short)1, "lat"),
    LNG((short)2, "lng");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // LAT
          return LAT;
        case 2: // LNG
          return LNG;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __LAT_ISSET_ID = 0;
  private static final int __LNG_ISSET_ID = 1;
  private BitSet __isset_bit_vector = new BitSet(2);

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LAT, new FieldMetaData("lat", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.DOUBLE)));
    tmpMap.put(_Fields.LNG, new FieldMetaData("lng", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.DOUBLE)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(GeocodePoint.class, metaDataMap);
  }

  public GeocodePoint() {
  }

  public GeocodePoint(
    double lat,
    double lng)
  {
    this();
    this.lat = lat;
    setLatIsSet(true);
    this.lng = lng;
    setLngIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GeocodePoint(GeocodePoint other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.lat = other.lat;
    this.lng = other.lng;
  }

  public GeocodePoint deepCopy() {
    return new GeocodePoint(this);
  }

  @Override
  public void clear() {
    setLatIsSet(false);
    this.lat = 0.0;
    setLngIsSet(false);
    this.lng = 0.0;
  }

  public double getLat() {
    return this.lat;
  }

  public GeocodePoint setLat(double lat) {
    this.lat = lat;
    setLatIsSet(true);
    return this;
  }

  public void unsetLat() {
    __isset_bit_vector.clear(__LAT_ISSET_ID);
  }

  /** Returns true if field lat is set (has been asigned a value) and false otherwise */
  public boolean isSetLat() {
    return __isset_bit_vector.get(__LAT_ISSET_ID);
  }

  public void setLatIsSet(boolean value) {
    __isset_bit_vector.set(__LAT_ISSET_ID, value);
  }

  public double getLng() {
    return this.lng;
  }

  public GeocodePoint setLng(double lng) {
    this.lng = lng;
    setLngIsSet(true);
    return this;
  }

  public void unsetLng() {
    __isset_bit_vector.clear(__LNG_ISSET_ID);
  }

  /** Returns true if field lng is set (has been asigned a value) and false otherwise */
  public boolean isSetLng() {
    return __isset_bit_vector.get(__LNG_ISSET_ID);
  }

  public void setLngIsSet(boolean value) {
    __isset_bit_vector.set(__LNG_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case LAT:
      if (value == null) {
        unsetLat();
      } else {
        setLat((Double)value);
      }
      break;

    case LNG:
      if (value == null) {
        unsetLng();
      } else {
        setLng((Double)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LAT:
      return new Double(getLat());

    case LNG:
      return new Double(getLng());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case LAT:
      return isSetLat();
    case LNG:
      return isSetLng();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GeocodePoint)
      return this.equals((GeocodePoint)that);
    return false;
  }

  public boolean equals(GeocodePoint that) {
    if (that == null)
      return false;

    boolean this_present_lat = true;
    boolean that_present_lat = true;
    if (this_present_lat || that_present_lat) {
      if (!(this_present_lat && that_present_lat))
        return false;
      if (this.lat != that.lat)
        return false;
    }

    boolean this_present_lng = true;
    boolean that_present_lng = true;
    if (this_present_lng || that_present_lng) {
      if (!(this_present_lng && that_present_lng))
        return false;
      if (this.lng != that.lng)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(GeocodePoint other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    GeocodePoint typedOther = (GeocodePoint)other;

    lastComparison = Boolean.valueOf(isSetLat()).compareTo(typedOther.isSetLat());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLat()) {
      lastComparison = TBaseHelper.compareTo(this.lat, typedOther.lat);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLng()).compareTo(typedOther.isSetLng());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLng()) {
      lastComparison = TBaseHelper.compareTo(this.lng, typedOther.lng);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // LAT
          if (field.type == TType.DOUBLE) {
            this.lat = iprot.readDouble();
            setLatIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // LNG
          if (field.type == TType.DOUBLE) {
            this.lng = iprot.readDouble();
            setLngIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    oprot.writeFieldBegin(LAT_FIELD_DESC);
    oprot.writeDouble(this.lat);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(LNG_FIELD_DESC);
    oprot.writeDouble(this.lng);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("GeocodePoint(");
    boolean first = true;

    sb.append("lat:");
    sb.append(this.lat);
    first = false;
    if (!first) sb.append(", ");
    sb.append("lng:");
    sb.append(this.lng);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

