(defproject core.matrix.dojo "0.0.1-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]   ;; Clojure iteself
                 
                 [net.mikera/core.matrix "0.22.0"]  ;; core.matrix
                 [net.mikera/vectorz-clj "0.21.0"]  ;; vectorz-clj implementation

                 [org.clojure/java.jdbc "0.3.3"]   ;; for JDBC database access
                 [org.clojure/data.csv "0.1.2"]    ;; for CSV loading etc
                 
                 [net.mikera/clojure-utils "0.6.0"] ;; various utility functions
              ]
  
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  
  :resource-paths ["src/main/resources"]
  
  :profiles {:dev {:resource-paths ["src/dev/resources"]
                   :dependencies []}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}}

)