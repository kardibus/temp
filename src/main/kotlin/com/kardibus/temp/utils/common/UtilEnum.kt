package com.kardibus.temp.utils.common

import model.Labeled

inline fun <reified T> mapNameToLabel() where T : Enum<T>, T : Labeled = enumValues<T>().associate { it.name to it.label }
