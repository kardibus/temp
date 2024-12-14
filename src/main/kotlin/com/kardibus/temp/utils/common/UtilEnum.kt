package com.kardibus.temp.utils.common

inline fun <reified T> mapNameToLabel() where T : Enum<T>, T : Labeled = enumValues<T>().associate { it.name to it.label }
