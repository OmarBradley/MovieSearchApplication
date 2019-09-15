package omarbradley.com.util

inline fun <T, R> withNullable(receiver: T?, block: T.() -> R): R? {
    return receiver?.block()
}

// lazyMap 과 withDefault 에 대한 설명을 보려면
// https://www.notion.so/omarbradley/Map-48b1b14ac4d74a868cae593dd41d3c52 를 참고하라
inline fun <K, V> lazyMap(crossinline initializer: (K) -> V): Map<K, V> {
    val map = mutableMapOf<K, V>()
    return map.withDefault { key ->
        val newValue = initializer(key)
        map[key] = newValue
        return@withDefault newValue
    }
}
