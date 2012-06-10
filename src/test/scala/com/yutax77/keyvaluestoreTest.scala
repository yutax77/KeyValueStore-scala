package com.yutax77
import org.scalatest.FlatSpec

class KeyValueStoreTest extends FlatSpec {
    it should "put data and get it" in {
        val store = new KVStore();
        store.put("A", "val1")
        store.put("B", "val2")
        assert(store.get("A") === "val1")
        assert(store.get("B") === "val2")
    }

    it should "throw IllegalArgumentException by put Null key" in {
        val store = new KVStore()
        intercept[IllegalArgumentException] {
            store.put(null, "val1")
        }
    }

    it should "put null value" in {
        val store = new KVStore()
        store.put("A", null)
        assert(store.get("A") === null)
    }

    it should "dumped stored items" in {
        val store = new KVStore()
        store.put("A", "val1")
        store.put("B", "val2")

        val expected = List(("A", "val1"), ("B", "val2"))
        assert(store.dumpImpl() === expected)
    }
}
