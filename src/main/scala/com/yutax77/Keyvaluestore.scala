package com.yutax77
import scala.collection.mutable.ListBuffer
import java.util.Calendar

class KVStore {
    var stored = Map.empty[String, (String, Calendar, String)]

    def put(key:String, value:String):Unit = {
        if(key == null)
          throw new IllegalArgumentException()

        stored = stored + (key -> (value, Calendar.getInstance(), key))
    }

    def putList(list:List[(String, String)]):Unit = {
      list.foreach{ kv =>
        put(kv._1, kv._2)
      }
    }
    
    def get(key:String):Option[String] = {
        val result = stored.get(key)
        result match {
            case None => None
            case Some((a, b, c)) => Some(a)
        }
    }

    def dump():Unit = {
        val list = dumpImpl()
        list.foreach { case (key, value) =>
            println(key + " : " + value)
        }
    }
    
    def dumpImpl():List[(String, String)] = {
        val buf = new ListBuffer[(String, String)]
        println(stored.size)
        val calendarList = stored.values.toList.map{v => (v._2, v._3)}.sortBy{v => v._1}.reverse
        calendarList.foreach { v =>
            val value = get(v._2)
            value match {
                case None => throw new AssertionError
                case Some(a) => buf += (v._2 -> a)
            }
        }
        buf.toList
    }

    def delete(key:String): Unit = {
        if(key == null)
          throw new IllegalArgumentException()

        stored = stored - key
    }
}
