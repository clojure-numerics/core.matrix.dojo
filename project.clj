(defproject core.matrix.dojo "0.0.1-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 
                 [org.clojure/java.jdbc "0.3.3"]
                 [net.mikera/clojure-utils "0.6.0"]
                 [net.mikera/core.matrix "0.21.0"]
                 [net.mikera/vectorz-clj "0.20.0"]
              ]
  
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  
  :resource-paths ["src/main/resources"]
  
  :profiles {:dev {:resource-paths ["src/dev/resources"]
                   :dependencies []}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}}

)