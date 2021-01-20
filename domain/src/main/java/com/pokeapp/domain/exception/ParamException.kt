package com.pokeapp.domain.exception

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

open class ParamException(message: String, title: String? = null) :
    DomainException(message, title)