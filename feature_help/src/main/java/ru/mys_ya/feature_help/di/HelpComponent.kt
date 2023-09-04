package ru.mys_ya.feature_help.di

import ru.mys_ya.feature_help.ui.HelpFragment

fun interface HelpComponent {
    fun injectHelpFragment(helpFragment: HelpFragment)
}
