#!/usr/bin/python

import os
import sys
from optparse import OptionParser

usage = "usage: %prog [options] hfile_directory"
parser = OptionParser(usage = usage)
parser.add_option("--host", dest="host",  default="0.0.0.0", type='string',
  help="host")
parser.add_option("-p", "--port", dest="port",  default=8080, type='int',
  help="port")
parser.add_option("--warmup", dest="warmup",  default=False, action='store_true',
  help="warmup service, increases startup time")
parser.add_option("--preload", dest="preload",  default=False, action='store_true',
  help="preload index to prevent coldstart, increases startup time")
parser.add_option("--nopreload", dest="preload",  default=False, action='store_false',
  help="don't preload index to prevent coldstart, decrease startup time, increases intial latency")
parser.add_option("-r", "--rebel", dest="rebel",  default=False, action='store_true',
  help="rebel")


(options, args) = parser.parse_args()

if len(args) != 1:
  parser.print_usage()
  sys.exit(1)

basepath = os.path.abspath(args[0])

sbt = './sbt'
if options.rebel:
  sbt = './sbt-rebel'

args = ' --preload %s --warmup %s ' % (options.preload, options.warmup)


cmd = '%s "server/run-main com.foursquare.twofishes.GeocodeFinagleServer %s --host %s --port %d --hfile_basepath %s"' % (sbt, args, options.host, options.port, basepath)
print(cmd)
os.system(cmd)

