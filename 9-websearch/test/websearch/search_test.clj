(ns websearch.search-test
  (:require [clojure.test :refer :all]
            [websearch.search.bing :as bing]
            [websearch.search.google :as google]
            [websearch.search.yahoo :as yahoo]
            [clojure.java.io :as io]))

;; TODO: Make this a macro
(defn is-equal [a b]
  (is (= a b)))

;; Bing
(deftest bing-search-url
  (testing "should be formatted correctly"
    (is (= (bing/search-url "Potato Farm") "https://www.bing.com/search?q=Potato%20Farm"))))

(deftest bing-first-search-result
  (testing "should be the expected value"
    (-> (slurp (io/file (io/resource "test/bing-NAND.html")))
        (bing/find-first-result)
        (is-equal "https://en.wikipedia.org/wiki/NAND_gate"))))

; ; Google
(deftest google-search-url
  (testing "should be formatted correctly"
    (is (= (google/search-url "Potato Farm") "https://www.google.com/search?q=Potato%20Farm"))))

(deftest google-first-search-result
  (testing "should be the expected value"
    (-> (slurp (io/file (io/resource "test/google-NAND.html")))
        (google/find-first-result)
        (is-equal "/url?q=https://en.wikipedia.org/wiki/NAND_gate&sa=U&ved=0ahUKEwjJprip4PbKAhUBfSYKHYvMB8wQFggcMAI&usg=AFQjCNG9fV98wVarO142Vj9Px3Lp4ZNtQQ"))))

;; Yahoo
(deftest yahoo-search-url
  (testing "should be formatted correctly"
    (is (= (yahoo/search-url "Potato Farm") "https://search.yahoo.com/search?p=Potato%20Farm"))))

(deftest yahoo-first-search-result
  (testing "should be the expected value"
    (-> (slurp (io/file (io/resource "test/yahoo-NAND.html")))
        (yahoo/find-first-result)
        (is-equal "http://r.search.yahoo.com/cbclk2/dWU9OTRFQzc4MEE3OUE3NEE0MyZ1dD0xNDU1NDM1NTU5MzU1JnVvPTgxNzY3NjAwOTUmbHQ9Mg--/RV=2/RE=1455464359/RO=10/RU=http%3a%2f%2f51000061.r.msn.com%2f%3fld%3dd3hMT_vkJM3zof6gvhp3honzVUCUzy2RY03o_lqBw9j1HwEhW3679_-XNkNVQ1yHgJ0FSjvB5j53wgLv584AB0uO-xEmaEX03b4KDJrMhP-v9MweAxildtOpJ3Z3FtyJzDXNncKng5b0jpfr4w0DVzD_5V8EjNPoNXFQoMQG58Gj6qOj5a%26u%3dhttp%253a%252f%252findex.about.com%252fslp%253f%2526q%253dnand%252bflash%252bstorage%2526sid%253dae499c56-ad3c-4534-8dec-94bb22628c3a-0-ab_msb%2526kwid%253dnand%2526cid%253d8176760095/RK=0/RS=Z1vjKZpDUaG13pgXtRv.7XzVs8E-;_ylc=cnQDMQ--?p=NAND"))))
