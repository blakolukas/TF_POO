Desenvolver novos elementos interativos para um jogo de aventura 
 
Objetivo: 
O  objetivo  do  trabalho  é  desenvolver  novos  elementos  interativos  e  mecânicas  para  um  jogo  de 
aventura já existente. O código-fonte inicial fornecido já implementa o controle da interface gráfica e 
a mecânica básica de movimento do personagem. 
Descrição do jogo: 
O jogo permite que o jogar movimente um personagem por um mapa usando coordenadas x e y.  O 
mapa  possui  tamanho  de  800x600  pixels  e  é  dividido  em  células  de  10  pixels  cada,  formando  uma 
matriz de 80x60 células.  Capa elemento do mapa é posicionado em uma única célula. O personagem 
se movimenta nas 4 direções (cima, baixo, esquerda e direita) uma célula por vez. 
O mapa é carregado na inicialização a partir de um arquivo de texto. O arquivo deve conter 60 linhas 
de 80 caracteres.  Cada caractere representa um elemento que será desenhado no mapa do jogo. Por 
exemplo, no código disponibilizado temos os seguintes caracteres (ver arquivo mapa.txt): 
• Espaço:  deixa a célula vazia no mapa; 
• #: desenha uma parede de tijolos; 
• V: desenha vegetação; 
• P: representa a posição inicial do personagem no mapa. 
O mapa inicia  com  todas  as  células  pintadas  de  cinza (efeito  fog)  e  vai sendo  revelador  conforme  o 
jogador movimenta o personagem. O jogador pode utilizar tanto as teclas do teclado (WASD) quando 
o mouse através dos botões da interface. A imagem a seguir mostre um exemplo  de experiência de 
usuário no jogo. 
 
O  diagrama  de  classes  a  seguir  apresenta  a  estrutura  interna  do  programa  disponibilizado.  Toda  a 
lógica  de  criação  e  atualização  da  interface  gráfica  está  encapsulada  dentro  da  classe  Jogo.  O 
posicionamento dos elementos do  mapa nas células e a movimentação e ações do personagem são 
implementados  na  classe  Mapa.  A  interface  ElementoMapa permite  implementar  novas  elementos 
que podem ser adicionados ao mapa e sofrer algum tipo de interação com o personagem. Veja mais 
informações nos comentários no código-fonte. 
 
 
Implementação de novos elementos 
Você deve desenvolver pelo menos dois novos elementos para o mapa. Cada elemento deve ter: 
• Uma  representação  visual  única,  definida  por  um  símbolo  e  cor.  Sugestão:  pesquisar  por 
símbolos interessantes na tabela de caracteres Unicode. 
• Efeitos ou interações específicas com o personagem. 
• Sugestões  de  elementos:  obstáculos,  itens  colecionáveis,  armadinhas,  portais,  NPCs  (Non-
Player Characters) com diálogos, etc. 
• Exemplos de posicionamento no mapa. 
Implementação da ação de interação do personagem 
Você deve implementar a ação de interagir do personagem. Atualmente o método interagir da classe 
Mapa  apenas  retorna  uma  mensagem  genérica.  Você  deve  implementar  esse  método  para  que 
funciona da seguinte forma: 
• Pesquisar  elementos  interagíveis  um  raio  de  2  célula  de  distância  da  posição  atual  do 
personagem; 
• Chamar o interage() do elemento encontrado para obter a mensagem de interação; 
• Caso  seja  encontrado  mais  de  um  elemento,  priorizar  o  mais  próximo  e  definir  um  critério 
padrão de desempate elementos com a mesma distância. 
• Criar exemplos de posicionamento no mapa que permitam testar a funcionalidade. 
• (Extra) Desenvolver mecânicas de interação entre o personagem e os novos elementos. 
• (Extra) Fazer com que essas interações afetam o personagem e/ou o progresso no jogo. 
 
Entrega 
O trabalho pode ser realizado em grupos de até 3 integrantes desde que a equipe esteja cadastrada 
como “grupo” no Moodle. A entrega será feita em duas partes. A entrega final deverá ser feita como 
um arquivo “.zip” contendo os arquivos fonte desenvolvidos,  bem  como  informações  para  sua 
compilação  e  execução.  Escreva  também  um  pequeno  relatório  explicando  os  novos  elementos  e 
interações implementadas. Atente para os prazos de entregas e apresentação definidos no Moodle.








Trabalho Final POO -Jogo de Aventura- 2023/2 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
Nome: Lucas Guerbari Blacovicz 
Implementação de novos elementos  
Para este trabalho, implementei 4 novos elementos para o mapa: 
 
Choque: caso o personagem toque (caminhe) no choque, seu jogo fechará, significando 
que morreu e terá que recomeçar o jogo. Para implementa-lo me inspirei no método 
podeMover porém, com a lógica voltada para que quando o jogador escolher caminhar 
em direção ao choque, ao invés de não poder mover, ele morre. 
 
Gerador: o gerador é responsável por desligar os choques, possibilitando que o jogador 
progrida no jogo (no mapa que dei de exemplo). Para utiliza-lo é usado o método 
interage. O método irá buscar um gerador nas proximidades do jogador e caso o 
encontre, o ligará. É possível saber se o gerador está ligado ou desligado por sua cor 
(vermelho quando deligado, verde quando ligado). 
 
Espinhos: te impedem de passar. É necessário destruí-los para passar utilizando o 
método ataca. O método, na verdade, procura caracteres que estão relacionados aos 
espinhos (‘E’) nas proximidades do jogador e os substitui para caracteres vazios (‘ ’) no 
mapa. 
 
Nada: o elemento, na verdade, não tem outra funcionalidade a não ser substituir os 
choques no mapa. 
 
Efeito fog também foi alterado a fim de diminuir a visão do jogador (dificultar).
