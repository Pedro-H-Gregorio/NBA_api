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

## 🚀 Conclusão

Este **banco de dados da NBA** fornece uma **estrutura organizada e robusta** para o gerenciamento e análise de dados, facilitando a consulta e visualização de informações essenciais sobre a liga, equipes, jogadores e suas estatísticas. Com as abordagens de ETL e API, o sistema assegura que os dados sejam mantidos atualizados e acessíveis para diversas análises e aplicações.

---

**Contribuições e sugestões são sempre bem-vindas! 🤗**
