package com.yutax77
import org.scalatest.FlatSpec

class KeyValueStoreTest extends FlatSpec {
    it should "put data and get it" in {
        val store = new KVStore();
        store.put("A", "val1")
        store.put("B", "val2")
        assert(store.get("A") === Some("val1"))
        assert(store.get("B") === Some("val2"))
        assert(store.get("C") === None)
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
        assert(store.get("A") === Some(null))
    }

    it should "dumped stored items" in {
        val store = new KVStore()
        store.put("A", "val1")
        store.put("B", "val2")

        val expected = List(("B", "val2"), ("A", "val1"))
        assert(store.dumpImpl() === expected)
    }

    it should "delete key-value" in {
        val store = new KVStore()
        store.put("A", "val1")
        store.put("B", "val2")

        assert(store.get("A") === Some("val1"))
        assert(store.get("B") === Some("val2"))

        store.delete("A")
        store.delete("C")
        assert(store.get("A") === None)
        assert(store.get("B") === Some("val2"))
    }

    it should "throw IllegalArgumentException by delete Null key" in {
        val store = new KVStore()
        intercept[IllegalArgumentException] {
            store.delete(null)
        }
    }

    it should "be update value By already exist key" in {
        val store = new KVStore()
        store.put("A", "val1")
        assert(store.get("A") === Some("val1"))

        store.put("A", "val100")
        assert(store.get("A") === Some("val100"))
    }

    it should "be put multi key-values" in {
        val store = new KVStore()
        val list = List(("A", "val1"), ("B", "val2"), ("A", "val100"))
        store.putList(list)
        assert(store.get("A") === Some("val100"))
        assert(store.get("B") === Some("val2"))
    }

    it should "be throw exception by include null value " in {
        val store = new KVStore()
        val list = List(("A", "val1"), (null, "val2"))
        intercept[IllegalArgumentException] {
            store.putList(list)
        }
    }
}
