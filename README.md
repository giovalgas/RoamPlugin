<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/giovalgas/Mines">
    <img src="https://i.pinimg.com/564x/51/b9/b3/51b9b3db5da0b94626e90b1655730fff.jpg" alt="Logo" width="128" height="128">
  </a>

  <h3 align="center">Roam Plugin</h3>

  <p align="center">
    Adds freecam to minecraft! <br />
    Supports 1.8.8-1.16.5
    <br />
    <br />
    <a href="https://youtu.be/iNT8EAlLA-c">View Demo</a>
    ·
    <a href="https://github.com/giovalgas/RoamPlugin/issues">Report Bug</a>
    ·
    <a href="https://github.com/giovalgas/RoamPlugin/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
  * [Configuration](#configuration)
* [Usage](#usage)
* [Contributing](#contributing)
* [Contact](#contact)

<!-- ABOUT THE PROJECT -->
## About The Project
<table>
  <tr>
    <td><img src="https://i.gyazo.com/172dc9b129cf236993a609f0bf2dbf7c.gif"></td>
    <td><img src="https://i.gyazo.com/7393acfc945863c4224cc46ceebdcbec.gif"></td>
  </tr>
 </table>

Although this was made with the intent of adding it to my portfolio I fully intent to keep fixing bugs.

Features:
* Vault support.
* Configurable.
* No lag.
* Supports most versions of mc.


### Built With
This project was built with:
* [Spigot](https://www.spigotmc.org/)
* [Java](https://java.com/pt-BR/)
* [Vault](https://dev.bukkit.org/projects/vault)
* [XSeries](https://github.com/CryptoMorin/XSeries)
* [NBTAPI](https://www.spigotmc.org/resources/nbt-api.7939/)

### Support the dev

Enjoyed the plugin and want to support me monetarily? [buy me a coffe!](https://www.buymeacoffee.com/giovalgasdev)
Any donations are going to be **greatly appreciated!**
<!-- GETTING STARTED -->
## Getting Started

To get this plugin up and running you will only need to follow the following steps.

### Prerequisites

You will need these installed in your server before proceeding.

* [Vault](https://dev.bukkit.org/projects/vault) (if you're using the vault hook)
* [Spigot](https://www.spigotmc.org/)


### Installation

1. Download the plugin at [https://github.com/giovalgas/RoamPlugin/releases](https://github.com/giovalgas/Mines/releases)
2. Put the downloaded jar into the plugins folder (yourServerFolder/plugins)

### Configuration

```yaml
#Config.yml - Template Plugin  

console-prefix: "[RoamPlugin] "  

roam:  
  max-distance: 48 # max distance a player can travel while roaming (in blocks)  
  duration: -1 # maximum duration of a roaming session (in seconds)  
  cooldown: 60 # cooldown between roaming sessions (in seconds)  

 # Disables commands while roaming # This can be overwritten if the player in question has the permission "roamplugin.command.<command>" # This can also be overwritten if the player has "roamplugin.admin"  disable-commands: true  

# PS: for pricing to work in server you'll need Vault and an Economy provider  
# Vault: https://www.spigotmc.org/resources/vault.34315/  
# Economy Provider(doesn't need to be this one): https://www.spigotmc.org/resources/essentialsx.9089/  
vault-hook:  
  price: 100 # price to roam  
  enabled: false # whether vault is enabled or not in your server
```

### Language

```yaml
#Language.yml - RoamPlugin

messages:  
  message-prefix: "&c[&6RoamPlugin&c] "  
  no-permission: "&cYou don't have the permission needed to execute that command."  

  still-on-cooldown: "&cWait more &e%cooldown_time% &cseconds before using that again."  
  session-expired: "&eYour roaming session has &cexpired&e!"  

  flying-error: "&cYou cant use roam while flying."  
  interaction-error: "&cYou can't do that while roaming."  
  command-error: "&cYou can't use that command while roaming."  
  movement-error: "&cYou can't fly past that while roaming."  

  started-roaming: "&eYou are now on roaming mode!"  
  stopped-roaming: "&eYou are now out of roaming mode!"  

  no-money: "&cYou need &e$%price% &cto enter roaming mode."  
  payed-amount: "&eYou have paid &b$%price% &eto enter roaming mode!"
```

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- CONTACT -->
## Contact

Giovani Valgas - [@giovalgas](https://twitter.com/giovalgas) - giovalgascom@gmail.com

Project Link: [https://github.com/giovalgas/RoamPlugin](https://github.com/giovalgas/RoamPlugin)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/giovalgas/RoamPlugin.svg?style=flat-square
[contributors-url]: https://github.com/giovalgas/RoamPlugin/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/giovalgas/RoamPlugin.svg?style=flat-square
[forks-url]: https://github.com/giovalgas/RoamPlugin/network/members
[stars-shield]: https://img.shields.io/github/stars/giovalgas/RoamPlugin.svg?style=flat-square
[stars-url]: https://github.com/giovalgas/RoamPlugin/stargazers
[issues-shield]: https://img.shields.io/github/issues/giovalgas/RoamPlugin.svg?style=flat-square
[issues-url]: https://github.com/giovalgas/RoamPlugin/issues
