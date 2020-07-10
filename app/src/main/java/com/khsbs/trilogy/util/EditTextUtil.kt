package com.khsbs.trilogy.util

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.multilineIme(action: Int) {
    imeOptions = action
    inputType = EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE
    setHorizontallyScrolling(false)
    maxLines = 10
}