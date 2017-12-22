package me.leon.libs.engine.http

/**
 * Created by PC on 2017/12/22.
 */
data class HttpResponse<T> (val code:Int,val msg :String,val info :T )

