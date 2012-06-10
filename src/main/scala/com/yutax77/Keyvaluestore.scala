package com.yutax77
import scala.collection.mutable.ListBuffer

class KVStore {
    var stored = Map.empty[String, String]

    def put(key:String, value:String):Unit = {
        if(key == null)
          throw new IllegalArgumentException()

        stored = stored + (key -> value)
    }

    def get(key:String):String = {
        return stored(key)
    }

    def dump():Unit = {
        val list = dumpImpl()
        list.foreach { case (key, value) =>
            println(key + " : " + value)
        }
    }
    
    def dumpImpl():List[(String, String)] = {
        val buf = new ListBuffer[(String, String)]
        stored.foreach { item =>
            buf += item
        }

        buf.toList
    }
}
