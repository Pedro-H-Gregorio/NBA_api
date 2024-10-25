# 🌟 MINI MUNDO - Banco de Dados da NBA 🏀

## 📖 Descrição do Projeto

O projeto consiste em um **banco de dados estruturado** para armazenar e gerenciar informações abrangentes sobre a **NBA**, a liga de basquete mais famosa do mundo. A estrutura do banco permite a organização de dados relacionados às temporadas, equipes, jogos, jogadores e suas estatísticas, oferecendo mecanismos eficazes para análises e consultas. 

## 🏗️ Estrutura do Banco de Dados

### 📊 Tabelas Principais

1. **Temporadas 📅**
   - Identificada por um código único.
   - Inclui o ano correspondente a cada temporada, a partir de **1946**.

2. **Equipes 🏆**
   - Detalhes sobre cada equipe da NBA, como:
     - Cidade 🌆
     - Nome abreviado ✏️
     - Nome curto 🏷️
     - Nome completo 📜
     - Número de aparições no All-Star ⭐
   - Cada equipe é identificada por um código único.

3. **Equipe na Temporada 📈**
   - Relaciona cada equipe com a temporada em que participou.
   - Utiliza chaves estrangeiras para conectar as tabelas de equipes e temporadas.

4. **Jogos 🎮**
   - Registra detalhes das partidas, incluindo:
     - Data 📆
     - Horários de início e término ⏰
     - Nome da arena 🏟️
     - Cidade e país do jogo 🌍
     - Fase da temporada 📊
     - Status do jogo ✅
   - Conecta jogos às equipes envolvidas e à temporada correspondente.

5. **Jogadores 👤**
   - Informações registradas incluem:
     - Nome 🏷️
     - País de origem 🌎
     - Número da camisa 👕
     - Altura 📏
     - Peso ⚖️
     - Equipe à qual pertence 🏀
   - Cada jogador tem um código único para fácil identificação.

6. **Jogador na Temporada ⏳**
   - Relaciona jogadores com as temporadas específicas em que jogaram, permitindo análise do desempenho ao longo do tempo.

7. **Estatísticas dos Jogadores 📊**
   - Captura desempenho detalhado em cada jogo, incluindo:
     - Pontos 🏀
     - Rebotes ↕️
     - Bloqueios 🚫
     - Assistências 🤝
     - Roubos de bola 🏃‍♂️
     - Arremessos de três pontos 🎯
     - Cestas do perímetro ⭕
   - Associada a um jogador e a um jogo específico para análises detalhadas.

## 🔄 Abordagens para Popular o Banco de Dados

O projeto implementou **duas abordagens principais** para a inserção de dados no banco:

1. **ETL (Extract, Transform, Load) 🔄**
   - Utiliza scripts em Python 🐍 para consultar uma API, extrair, transformar e carregar dados estruturados diretamente no banco de dados.

2. **API com Endpoints 🌐**
   - Oferece um controle mais direto e manual dos dados.
   - Permite comunicação direta com o banco de dados através de chamadas de endpoint.
   - Possui interligação com o processo de ETL, onde uma chamada de endpoint pode acionar o ETL, inserindo automaticamente dados relevantes de uma temporada específica.

## 🖊️ Mini mundo Formal
O banco de dados da NBA é estruturado para armazenar e gerenciar informações abrangentes sobre a liga de basquete. Ele organiza dados relacionados às temporadas, equipes, jogos, jogadores e suas estatísticas, oferecendo mecanismos para análises e consultas. Cada **temporada** é identificada por um código único e inclui o ano correspondente, a partir de 1946, que marca o início da liga, esta tabela permite associar todas as informações subsequentes a períodos específicos da competição. A tabela de **equipes** contém detalhes sobre cada equipe da NBA, como a cidade, nome abreviado, nome curto, nome completo e o número de aparições no All-Star, cada equipe é identificada por um código único, facilitando a gestão e identificação das diferentes equipes. Para associar as equipes a temporadas específicas, utilizamos a tabela **equipe na temporada**, que relaciona cada equipe com a temporada em que participou, por meio de chaves estrangeiras que conectam a tabela de equipes e a tabela de temporadas. A tabela de **jogos** registra todos os detalhes das partidas realizadas, incluindo data, horários de início e término, nome da arena, cidade e país onde o jogo ocorreu, fase da temporada e status do jogo. Ela também conecta o jogo às equipes envolvidas e à temporada em que o jogo ocorreu, permitindo rastrear e analisar as partidas. Os **jogadores** são registrados em uma tabela específica que inclui informações como nome, país de origem, número da camisa, altura, peso e a equipe à qual pertencem.  Cada jogador tem um código único, o que facilita a identificação e gestão de dados individuais. Para analisar o desempenho dos jogadores ao longo das temporadas, a tabela **jogador na temporada** relaciona os jogadores com as temporadas específicas em que jogaram. Essa tabela permite acompanhar o desempenho dos jogadores em diferentes períodos da competição. Finalmente, a tabela de **estatísticas dos jogadores** captura o desempenho detalhado dos jogadores em cada jogo, ela inclui dados como pontos, rebotes, bloqueios, assistências, roubos de bola, arremessos de três pontos e cestas do perímetro. Cada conjunto de estatísticas está associado a um jogador e a um jogo específico, possibilitando análises detalhadas do desempenho dos jogadores em partidas individuais. No conjunto, o banco de dados da NBA fornece uma estrutura organizada para o gerenciamento e análise de dados, facilitando a consulta e a visualização de informações essenciais sobre a liga, equipes, jogadores e suas estatísticas.
Neste projeto, foram implementadas duas abordagens para popular o banco de dados. A primeira utiliza um processo de ETL (Extract, Transform, Load), no qual uma API é consultada por meio de scripts em Python para extrair, transformar e carregar os dados estruturados, inserindo-os adequadamente no banco de dados. A segunda abordagem oferece um controle mais direto e manual dos dados através de uma API com endpoints que permitem a comunicação direta com o banco de dados. Além disso, há uma interligação entre ambas as versões, permitindo que uma chamada de endpoint acione o processo de ETL, especificando uma temporada para a qual todos os dados relevantes serão inseridos no sistema de maneira automatizada e integrada.

## 🚀 Conclusão

Este **banco de dados da NBA** fornece uma **estrutura organizada e robusta** para o gerenciamento e análise de dados, facilitando a consulta e visualização de informações essenciais sobre a liga, equipes, jogadores e suas estatísticas. Com as abordagens de ETL e API, o sistema assegura que os dados sejam mantidos atualizados e acessíveis para diversas análises e aplicações.

---

**Contribuições e sugestões são sempre bem-vindas! 🤗**
