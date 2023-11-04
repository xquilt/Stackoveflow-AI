package com.polendina.soai.utils

import org.junit.jupiter.api.Test

class StringKtTest {

    private val values: Map<String, String> = mapOf(
        """<script type="application/json" data-role="module-args" data-module-name="Shared/options.mod">{"options":{"locale":"en","serverTime":1699089179,"routeName":"Users/Login","stackAuthUrl":"https://stackauth.com","networkMetaHostname":"meta.stackexchange.com","site":{"name":"Stack Overflow","description":"Q\u0026A for professional and enthusiast programmers","isNoticesTabEnabled":true,"enableNewTagCreationWarning":true,"insertSpaceAfterNameTabCompletion":false,"id":1,"cookieDomain":".stackoverflow.com","childUrl":"https://meta.stackoverflow.com","negativeVoteScoreFloor":null,"enableSocialMediaInSharePopup":true,"protocol":"https"},"user":{"fkey":"3ebc5c9dcbded294de011c5a8e83bbf3a88","tid":"a7c09-022f-446-9609-08e615a","rep":0,"isAnonymous":true,"isAnonymousNetworkWide":true,"flags":{"allowRetractingCommentFlags":true,"allowRetractingFlags":true},"elections":{"opaVoteResultsBaseUrl":"https://www.opavote.com/results/"},"comments":{},"accounts":{"currentPasswordRequiredForChangingStackIdPassword":true}}}</script> <script>StackExchange.init();</script>""".trimIndent().parseFKey()
        to "3ebc5c9dcbded294de011c5a8e83bbf3a88"
    )

    @Test
    fun parseFKey() {
        values.forEach { s1, s2 ->
            assert(s1 == s2)
        }
    }

}