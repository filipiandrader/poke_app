package com.dexapp.data_remote.exception

import com.dexapp.data.exception.ServerException
import com.dexapp.data_remote.utils.enums.ErrorMessageEnum

/*
 * Created by Filipi Andrade Rocha on 31/01/2021.
 */

class ServerException(
    message: String = ErrorMessageEnum.GENERIC_ERROR.message
) : ServerException(message)