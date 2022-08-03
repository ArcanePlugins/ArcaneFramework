<div align="center">

# ArcaneFramework

Shaded plugin development library for ArcanePlugins software

<a href="https://www.discord.io/arcaneplugins">
<img src="https://img.shields.io/badge/Chat%20%2F%20Support-on%20Discord-skyblue?style=for-the-badge&logo=discord&logoColor=white" alt="Support available on Discord">
</a>
<a href="https://www.spigotmc.org/conversations/add?to=lokka30">
<img src="https://img.shields.io/badge/Chat%20%2F%20Support-Spigot%20PM-skyblue?style=for-the-badge&logo=googlemessages" alt="Support available through Spigot PMs">
</a>

<br />

<a href="https://github.com/lokka30/ArcaneFramework/wiki">
<img src="https://img.shields.io/badge/Documentation-on%20Wiki-skyblue?style=for-the-badge&logo=wikipedia" alt="Documentation available on the Wiki">
</a>
<a href="https://github.com/lokka30/ArcaneFramework">
<img src="https://img.shields.io/badge/Source%20Code-on%20GitHub-skyblue?style=for-the-badge&logo=github" alt="Source Code available on GitHub">
</a>

<br />

<a href="https://github.com/lokka30/ArcaneFramework/wiki/Credits">
<img src="https://img.shields.io/badge/Contributors-View%20Credits-skyblue?style=for-the-badge" alt="Contributors listed in Credits Page">
</a>

</div>

## About

**ArcaneFramework** is a library purposed to reduce the amount of boilerplate code required
in our plugin suite.

## Should I use this library?

Probably not.

Unexpected, right? Usually libraries want to sell themselves to developers. 😄

There may be features you like in the library, however, there are a handful of barriers this library
poses to developers interested in adopting it. It boils down to how this library is designed for
our use - what *we* require of it - without catering for what other developers would desire it to be.
Rather, we have decided that forks of this library would be able to expand on it if wanted without
the burden of us trying to facilitate a feature we may never use or may not be able to maintain in
the long term. Here's why you probably *don't* want to use ArcaneFramework:

- Compiled in **Java 17**, so your plugin will need to be compiled with Java 17 or newer.
  - We will not backport as we are not interested in aiding users who intentionally use outdated
  software such as Java 8 and Minecraft 1.8, we would rather our software not be dragged behind.

- This library uses the **GNU GPL v3** license which poses restrictions on what license you may use
in your plugins which adopt it.
  - All of our plugins are GPL, and we don't like proprietary software.. the choice to make this
  framework licensed under GPL was an easy one to make.
  - **But...** if your plugin is licensed under the GPL, then all the better!

- This library is **highly opinionated**.
  - We have our way of doing things, our design choices in
  this library will reflect such.

- This library contains only the features that we require.
  - Again, feel free to create a fork if you want to expand on ArcaneFramework.

- Offers minimal documentation.
  - Enough that we (hopefully) won't forget what things do.

- Makes breaking changes as often as we need them to.
  - As we will have large codebases depending on ArcaneFramework, we will aim to not make large
  breaking changes so we don't have to constantly refactor things.
  - However, if there's something we need to change, we will do it.

**All of these barriers** (except for the license one) may be addressed by anyone who is willing to
create and maintain a fork of ArcaneFramework. We are not interested in maintaining such a variant
ourselves.

## Copyright Notice

Copyright © 2022 lokka30 and contributors

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
