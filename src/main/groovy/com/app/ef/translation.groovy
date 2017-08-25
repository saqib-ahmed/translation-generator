package com.app.ef
/**
 * Created by saqib ahmad on 8/23/2017.
 */

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder

class translation {
    public static void main(String[] args){
        def dir = args[0];
        def tl = args[1];
        def http = new HTTPBuilder('https://translate.googleapis.com')
//        def html = http.post(path : '/translate_a/single',
//                query : [client:"gtx", sl :"auto", tl: tl , dt:"t", q: "lorem ipsum sit dolor amet"])
//        println html[0][0][0]
        def inputFile = new File(dir)
        def inputJSON = new JsonSlurper().parse(inputFile)
//        println inputJSON
        inputJSON.each{k, v->
//            println "${k}:${v}"
            v.each{k1, v1->
                println "${k1}:${v1}"
                if(v1.class){
                    def html = http.post(path : '/translate_a/single',
                            query : [client:"gtx", sl :"auto", tl: tl , dt:"t", q: v1])
                    println html[0][0][0]
                }
            }
//            it.each{k, v ->
//                html = http.post(path : '/translate_a/single',
//                        query : [client:"gtx", sl :"auto", tl: tl , dt:"t", q: v])
//                println html[0][0][0]
//            }
        }
    }
}
