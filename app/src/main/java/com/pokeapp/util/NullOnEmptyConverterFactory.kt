package com.pokeapp.util

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by Filipi Andrade Rocha on 28/01/2020
 */

/**
 * Adicionar o suporte a "body" vazio ao Retrofit 2
 *
 * https://github.com/square/retrofit/issues/1554#issuecomment-178633697
 */
val nullOnEmptyConverterFactory = object : Converter.Factory() {
    fun converterFactory() = this
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any>(converterFactory(), type, annotations)
        return Converter<ResponseBody, Any> {
            if (it.contentLength() != 0L) {
                nextResponseBodyConverter.convert(it)
            } else {
                null
            }
        }
    }
}