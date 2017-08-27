package com.app.ef
/**
 * Created by saqib ahmad on 8/23/2017.
 */

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import groovy.json.JsonBuilder

class translation {
    static def x=1
    public static void main(String[] args){
        def obj = new translation()
        def dir = args[0];
        def tl = args[1];
        def outFile = new File("${tl}.json")
        if(outFile.exists())
          outFile.delete()

        def inputFile = new File(dir)
        inputFile.eachLine { line ->
          if(line.endsWith(',') || line.endsWith('\"')){
            def tok = line.tokenize(':')
            println tok[1]
            def out = obj.translate(tok[1], tl)
            println out
            def outLine = line.replaceAll(tok[1], out)
            println outLine
            outFile.append("\n"+outLine)
          } else{
            outFile.append("\n" + line)
          }
        }
    }

    def translate(def str, def tl){
      def http = new HTTPBuilder('https://translate.googleapis.com')
      def html
      try{
        if(x==1){
          println "Getting translation"
          html = http.post(path : '/translate_a/single',
                  query : [client:"gtx", sl :"auto", tl: tl , dt:"t", q: str])
          println html[0][0][0]
          return html[0][0][0]
        }
        else{
          throw new Exception()
        }
      }catch(Exception ex){
        x++
        str = str.replaceFirst('\"' , '\"${tl}_')
        return str + "_" + tl
      }
    }
}
