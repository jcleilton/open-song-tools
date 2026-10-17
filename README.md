# 🎵 Open DAW

Um projeto de Estação de Trabalho de Áudio Digital (DAW) de código aberto focado em [inserir foco, ex: performance, simplicidade, gravação e produção musical].

## 🚧 Status do Projeto
O projeto está em fase inicial de planejamento e estruturação. Nenhuma linha de código funcional foi implementada ainda. Fique à vontade para acompanhar o desenvolvimento!

## 🎯 Objetivos
- Criar um DAW leve, multiplataforma e extensível.
- Suporte a plugins de áudio (VST3, LV2, AU).
- Interface intuitiva para gravação, edição e mixagem de áudio e MIDI.

## 🤝 Como Contribuir
Como estamos nos estágios iniciais, sugestões de arquitetura, escolha de tecnologias e discussões sobre o design são muito bem-vindas! 
- Abra uma **Issue** para propor ideias ou relatar discussões.
- Participe das diretrizes iniciais de desenvolvimento.

## 📄 Licença e Apoio ao Projeto

### 🆓 100% Gratuito e Open Source
Este projeto é e sempre será **totalmente gratuito** para a comunidade. Ele é distribuído sob a licença livre **[Nome da Licença, ex: GNU General Public License v3.0 (GPLv3)]**, o que garante que você pode usar, estudar, modificar e redistribuir o software livremente, sem restrições de uso comercial ou pessoal.

### 💖 Contribuições Financeiras e Doações
O desenvolvimento, manutenção e evolução deste DAW dependem da colaboração da comunidade. 

- **Não cobramos** por licenças, assinaturas, recursos "premium" ou atualizações.
- Aceitamos e incentivamos **contribuições financeiras exclusivamente na forma de doações voluntárias**.
- Todo o valor arrecadado será destinado aos custos de infraestrutura, hospedagem e incentivo aos desenvolvedores e mantenedores do projeto.

Se você gosta do projeto e quer nos ajudar a continuar evoluindo, considere fazer uma doação:

- [ ] **Chave PIX:** [jcleilton@gmail.com]
- [ ] **Paypal:** [jcleilton@gmail.com]

*A sua contribuição voluntária mantém o software livre e acessível para todos!* 

This is a Kotlin Multiplatform project targeting Desktop (JVM).

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Desktop app:
  - Hot reload: `./gradlew :desktopApp:hotRun --auto`
  - Standard run: `./gradlew :desktopApp:run`

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Desktop tests: `./gradlew :shared:jvmTest`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…