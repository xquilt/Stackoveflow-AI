package com.polendina.soai

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class AskSoConMenAct: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        BrowserUtil.browse("")
    }
}