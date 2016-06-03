(defproject core.matrix.dojo "0.0.1-SNAPSHOT"
	:description "FIXME: write this!"
	:url "http://example.com/FIXME"
	
	:dependencies [[org.clojure/clojure "1.8.0"]   ;; Clojure iteself
	                 
	               [incanter "1.9.1"]

	               [net.mikera/core.matrix "0.49.0"]  ;; core.matrix
	               [net.mikera/vectorz-clj "0.41.0"]  ;; vectorz-clj implementation
	
	               [org.clojure/java.jdbc "0.4.2"]   ;; for JDBC database access
	               [org.clojure/data.csv "0.1.3"]    ;; for CSV loading etc
	                                 
	               [net.mikera/clojure-utils "0.6.2"] ;; various utility functions
	  ]
	:source-paths ["src/main/clojure"]
	:test-paths ["src/test/clojure"]
	  
	:resource-paths ["src/main/resources"]
	  
	:profiles {:dev {:resource-paths ["src/dev/resources"]
	                 :dependencies [[net.mikera/cljunit "0.3.1"]]
	                 :java-source-paths ["src/test/java"]}}
 )
