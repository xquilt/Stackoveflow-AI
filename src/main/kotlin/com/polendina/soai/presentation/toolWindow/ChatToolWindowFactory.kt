package com.polendina.soai.presentation.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import com.intellij.util.ui.JBUI
import com.polendina.soai.data.network.So
import com.polendina.soai.models.Query
import com.polendina.soai.models.QueryType
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.time.LocalTime
import javax.swing.DefaultListModel
import javax.swing.JList
import javax.swing.JTextPane
import javax.swing.ListCellRenderer

class ChatToolWindowFactory : ToolWindowFactory {

    private val so: So
    init {
        so = So()
        so.authenticateUser(
            email = "",
            password = ""
        )
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val chatToolWindow = ChatToolWindow(toolWindow = toolWindow, so = so)
        val content = ContentFactory.getInstance().createContent(chatToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class ChatToolWindow(toolWindow: ToolWindow, val so: So) {

        private val contentPanel: JBPanel<JBPanel<*>> = JBPanel(BorderLayout())
        private var answersList: JBList<Query>
        private val answerListModel: DefaultListModel<Query> = DefaultListModel()
        private var questionInputField: JBTextArea

        init {

            answersList = JBList(answerListModel)
            answersList.cellRenderer = QueryListCellRenderer()
            val scrollPane = JBScrollPane(answersList)
            contentPanel.add(scrollPane)

            val inputPanel: JBPanel<JBPanel<*>> = JBPanel(FlowLayout(FlowLayout.LEFT))

            questionInputField = JBTextArea(2, 4)
            questionInputField.preferredSize = Dimension(400, 100)
            questionInputField.addKeyListener(object: KeyAdapter() {
                override fun keyPressed(e: KeyEvent) {
                    if (e.isControlDown && e.keyCode == KeyEvent.VK_ENTER) {
                        askQuestion()
                    }
                }
            })
            inputPanel.add(questionInputField)

            contentPanel.add(inputPanel, BorderLayout.SOUTH)

        }

        private class QueryListCellRenderer: ListCellRenderer<Query> {
            override fun getListCellRendererComponent(
                p0: JList<out Query>?,
                p1: Query?,
                p2: Int,
                p3: Boolean,
                p4: Boolean
            ): Component = JTextPane().apply {
                if (p1?.type == QueryType.QUESTION) {
                    background = JBUI.CurrentTheme.TabbedPane.FOCUS_COLOR
                    border = JBUI.Borders.empty(5)
                    minimumSize = Dimension(width, height + 400)
                } else {
                    background = JBUI.CurrentTheme.TabbedPane.HOVER_COLOR
                    border = JBUI.Borders.empty(10)
                    contentType = "text/html"
                }
                text = p1?.query
            }
        }

        fun askQuestion() {
            val question = questionInputField.text.trim()

            if (question.isNotEmpty()) {
                answerListModel.addElement(
                    Query(
                    query = question,
                    time = LocalTime.now(),
                    type = QueryType.QUESTION,
                    id = question.hashCode()
                )
                )
                questionInputField.text = ""
                val answer = so.askQuestion(question)
                answerListModel.addElement(
                    Query(
                    query = answer?.Html ?: "N/A",
                    time = LocalTime.now(),
                    type = QueryType.ANSWER,
                    id = question.hashCode()
                )
                )
            }
        }

        fun getContent() = contentPanel

    }
}