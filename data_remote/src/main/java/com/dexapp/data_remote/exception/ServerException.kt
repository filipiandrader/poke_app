package com.dexapp.data_remote.exception

import com.dexapp.data_remote.utils.enums.ErrorMessageEnum
import com.dexapp.domain.exception.DataSourceException

/*
 * Created by Filipi Andrade Rocha on 31/01/2021.
 */

class ServerException(
    message: String = ErrorMessageEnum.GENERIC_ERROR.message
) : DataSourceException(message)