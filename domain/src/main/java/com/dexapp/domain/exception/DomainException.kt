package com.dexapp.domain.exception

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

open class DomainException(message: String, title: String? = null) :
    RuntimeException(message, RuntimeException(title))