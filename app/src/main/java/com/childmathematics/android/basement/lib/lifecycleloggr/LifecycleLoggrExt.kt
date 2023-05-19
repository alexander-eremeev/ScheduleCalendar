package com.childmathematics.android.basement.lib.lifecycleloggr

import android.os.Bundle
import android.os.Parcel


/**
 * Source from:
 *      https://github.com/guardian/toolargetool/blob/master/toolargetool
 *      /src/main/java/com/gu/toolargetool/TooLargeTool.kt#L162
 */
internal fun Bundle?.dataSize(): Int {
    if (this == null) return 0
/*
Container for a message (data and object references) that can be sent through an IBinder. A Parcel can contain both flattened data that will be unflattened on the other side of the IPC (using the various methods here for writing specific types, or the general Parcelable interface), and references to live IBinder objects that will result in the other side receiving a proxy IBinder connected with the original IBinder in the Parcel.
Parcel is not a general-purpose serialization mechanism. This class (and the corresponding Parcelable API for placing arbitrary objects into a Parcel) is designed as a high-performance IPC transport. As such, it is not appropriate to place any Parcel data in to persistent storage: changes in the underlying implementation of any of the data in the Parcel can render older data unreadable.
The bulk of the Parcel API revolves around reading and writing data of various types. There are six major classes of such functions available.
===================================
Контейнер для сообщения (ссылки на данные и объекты), которое можно отправить через IBinder. Parcel может содержать как сведенные данные, которые не будут сведены на другой стороне IPC (используя различные методы для записи определенных типов или общий интерфейс Parcelable), так и ссылки на активные объекты IBinder, которые приведут к тому, что другая сторона получит прокси-IBinder, связанный с исходным IBinder в Parcel.
Parcel не является универсальным механизмом сериализации. Этот класс (и соответствующий Parcelable API для размещения произвольных объектов в Parcel) разработан как высокопроизводительный IPC-транспорт. Таким образом, нецелесообразно помещать какие-либо данные Parcel в постоянное хранилище: изменения в базовой реализации любых данных в Parcel могут сделать старые данные нечитаемыми.
Основная часть API Parcel вращается вокруг чтения и записи данных различных типов. Имеется шесть основных классов таких функций.
 */
    val parcel = Parcel.obtain()

    return try {
        parcel.writeBundle(this)
        parcel.dataSize()
    } catch (e: Exception) {
        0
    } finally {
        parcel.recycle()
    }
}

internal fun Int.toKb(): Float {
    return this.toFloat() / 1000f
}
