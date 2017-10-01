package com.github.badoualy.telegram.tl.api

import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_CONSTRUCTOR_ID
import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT32
import com.github.badoualy.telegram.tl.core.TLLongVector
import com.github.badoualy.telegram.tl.serialization.TLDeserializer
import com.github.badoualy.telegram.tl.serialization.TLSerializer
import java.io.IOException

/**
 * updateStickerSetsOrder#bb2d201
 *
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
class TLUpdateStickerSetsOrder() : TLAbsUpdate() {
    @Transient
    var masks: Boolean = false

    var order: TLLongVector = TLLongVector()

    private val _constructor: String = "updateStickerSetsOrder#bb2d201"

    override val constructorId: Int = CONSTRUCTOR_ID

    constructor(masks: Boolean, order: TLLongVector) : this() {
        this.masks = masks
        this.order = order
    }

    protected override fun computeFlags() {
        _flags = 0
        updateFlags(masks, 1)
    }

    @Throws(IOException::class)
    override fun serializeBody(tlSerializer: TLSerializer) = with (tlSerializer)  {
        computeFlags()

        writeInt(_flags)
        writeTLVector(order)
    }

    @Throws(IOException::class)
    override fun deserializeBody(tlDeserializer: TLDeserializer) = with (tlDeserializer)  {
        _flags = readInt()
        masks = isMask(1)
        order = readTLLongVector()
    }

    override fun computeSerializedSize(): Int {
        computeFlags()

        var size = SIZE_CONSTRUCTOR_ID
        size += SIZE_INT32
        size += order.computeSerializedSize()
        return size
    }

    override fun toString() = _constructor

    override fun equals(other: Any?): Boolean {
        if (other !is TLUpdateStickerSetsOrder) return false
        if (other === this) return true

        return _flags == other._flags
                && masks == other.masks
                && order == other.order
    }
    companion object  {
        const val CONSTRUCTOR_ID: Int = 0xbb2d201.toInt()
    }
}