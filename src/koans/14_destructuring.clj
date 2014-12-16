(ns koans.14-destructuring
  (:require [koan-engine.core :refer :all]))

(def test-address
  {:street-address "123 Test Lane"
   :city "Testerville"
   :state "TX"})

(meditations
  "Destructuring is an arbiter: it breaks up arguments"
  (= ":bar:foo" ((fn [[a b]] (str b a))
         [:foo :bar]))

  "Whether in function definitions"
  (= (str "First comes love, "
          "then comes marriage, "
          "then comes Clojure with the baby carriage")
     ((fn [[a b c]] (str "First comes " a ", then comes " b ", then comes " c " with the baby carriage"))
      ["love" "marriage" "Clojure"]))

  "Or in let expressions"
  (= "Rich Hickey aka The Clojurer aka Go Time aka Macro Killah"
     (let [[first-name last-name & aliases]
           (list "Rich" "Hickey" "The Clojurer" "Go Time" "Macro Killah")]
       (str first-name " " last-name (reduce #(str %1 " aka " %2) "" aliases))))

  "You can regain the full argument if you like arguing"
  (= {:original-parts ["Stephen" "Hawking"] :named-parts {:first "Stephen" :last "Hawking"}}
     (let [[first-name last-name :as full-name] ["Stephen" "Hawking"]]
       {:original-parts full-name
        :named-parts {:first "Stephen" :last "Hawking"}}))

  "Break up maps by key"
  (= "123 Test Lane, Testerville, TX"
     (let [{street-address :street-address city :city state :state} test-address]
       (str street-address ", " city ", " state)))

  "Or more succinctly"
  (= "123 Test Lane, Testerville, TX"
     (let [{:keys [street-address city state]} test-address]
       (str street-address ", " city ", " state)))

  "All together now!"
  (= "Test Testerson, 123 Test Lane, Testerville, TX"
     (let [[first-name last-name] ["Test" "Testerson"]
           {:keys [street-address city state]} test-address]
       (str first-name " " last-name ", " street-address ", " city ", " state))))
