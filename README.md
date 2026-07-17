# Catálogo de Livros

Aplicativo Android para consulta e compra simulada de livros, desenvolvido para a atividade acadêmica de Desenvolvimento para Dispositivos Móveis II.

**Aluno:** Leandro Sobrinho Auzier

## Links da aplicação

- **Código-fonte no GitHub:**  
  https://github.com/leandroauzier/CatalogoDeLivros

- **Download do aplicativo em APK:**  
  https://github.com/leandroauzier/CatalogoDeLivros/BUILD_APK

O código-fonte completo está disponível neste repositório. O arquivo APK para instalação pode ser baixado na pasta **BUILD_APK**.

## Funcionalidades

- Login local com sessão persistida.
- Catálogo com 12 livros de exemplo.
- Pesquisa por título, autor, tema e assunto.
- Tela de detalhes com disponibilidade e quantidade em estoque.
- Carrinho com controle de quantidade, subtotal, total, limpeza e finalização simulada.
- Atendimento por e-mail e WhatsApp utilizando `Intent`.
- Diálogo "Sobre o aplicativo" com data e hora atuais.
- Opção de logout.

## Tecnologias utilizadas

- Java
- Android Views com XML
- Material Design 3
- RecyclerView
- SharedPreferences
- Gradle Kotlin DSL

## Credenciais de demonstração

- **Usuário:** `aluno`
- **Senha:** `123456`

## Como executar no Android Studio

1. Clone ou baixe este repositório.
2. Abra a pasta do projeto no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Selecione um dispositivo físico ou emulador com Android 7.0, API 24, ou superior.
5. Execute a configuração `app`.

Para clonar o projeto pelo terminal:

```bash
git clone https://github.com/leandroauzier/CatalogoDeLivros.git