# Match Simulator


<h3>App desenvolvido em Kotlin como simulador de partidas de futebol.<h3>
 
 O app tem como objetivo randomizar resultados de partidas de futebol.
 As bandeiras, a força do time (estrelas) e a imagem do campo são renderizadas através de uma API.
 
 
 
<h2> Tela inicial do app <h2>
 
![Screen](https://user-images.githubusercontent.com/104375832/176182193-106445a6-7064-4720-a6f1-acf8cf45bc91.png) 
 
 


  
<h2> Floating Action Button adicionado para randomização dos resultados<h2>
 
   Ao Clicar no botão flutuante, os resultados das partidas são alterados de forma aleatória, porém, dependendo da força do time ele tem mais probabilidade de fazer gols, sendo o máximo 5 que é o máximo de estrelas.
 
![fab](https://user-images.githubusercontent.com/104375832/176179492-0cf47c78-0757-42a5-aae8-351b5ccc347d.png)


 
 
 
 
 
<h2> Swipe Refresh Layout <h2>
 
 Ao utilizar do recurso de swipe refresh, o app puxa os dados da API novamente, e caso algo tenha sido alterado na API, o app mudará. No caso de já ter clicado no botão flutuante ateriormente, o swipe irá zerar os resultados das partidas, pois retornará o app ao nível de referencia (API).
 
 ![swiperefresh](https://user-images.githubusercontent.com/104375832/176180694-75a27a9d-efb6-40f6-b000-76ab20065ae0.png)
 
 
 
 
<h2> Detail Activity <h2>
 
 Tela de detalhe de partida 
 
  Quando é clicado em uma partida, essa abrirá uma tela de detalhe com a força dos times representados por uma rating bar e mostrará uma imagem do campo que acontece o jogo.
  Caso os resultados ja estivessem aparentes na tela inicial, esses se manterão na tela de detalhes.
   
 ![detail](https://user-images.githubusercontent.com/104375832/176181163-25e1c3e8-370c-4dd0-a588-5731005ae0df.png)
 


    
