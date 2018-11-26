encontrar_solucion(Estado,Movidas) :- resolver(Estado,[], Movidas).

resolver(Estado,_,[]) :- objetivo(Estado).

resolver(Estado,EstadoAnteriores,[PrimeraMovida | SiguienteMovidas]) :-
 movida_valida(Estado, EstadoAnteriores, PrimeraMovida, EstadoNuevo),
 resolver(EstadoNuevo, [Estado|EstadoAnteriores], SiguienteMovidas).

movida_valida(Estado, EstadoAnteriores, Movida, EstadoNuevo) :-
 encontrar_movida(Estado, Movida),
 aplicar_movida(Estado, Movida, EstadoNuevo),
 estado_nuevo(EstadoNuevo, EstadoAnteriores).

objetivo([1/2/3/8/0/4/7/6/5]).

estado_nuevo(S,P) :-
        not(member(S,P)).

%Puzzle Movidas


encontrar_movida(Estado,izquierda):-
                       izquierda(Estado).

encontrar_movida(Estado,derecha):-
                        derecha(Estado).

encontrar_movida(Estado,arriba):-
                     arriba(Estado).

encontrar_movida(Estado,abajo):-
                       abajo(Estado).

izquierda([A/0/C/D/E/F/G/H/I]).
izquierda([A/B/0/D/E/F/G/H/I]).
izquierda([A/B/C/D/0/F/G/H/I]).
izquierda([A/B/C/D/E/0/G/H/I]).
izquierda([A/B/C/D/E/F/G/0/I]).
izquierda([A/B/C/D/E/F/G/H/0]).


arriba([A/B/C/0/E/F/G/H/I]).
arriba([A/B/C/D/0/F/G/H/I]).
arriba([A/B/C/D/E/0/G/H/I]).
arriba([A/B/C/D/E/F/0/H/I]).
arriba([A/B/C/D/E/F/G/0/I]).
arriba([A/B/C/D/E/F/G/H/0]).

derecha([0/B/C/D/E/F/G/H/I]).
derecha([A/0/C/D/E/F/G/H/I]).
derecha([A/B/C/0/E/F/G/H/I]).
derecha([A/B/C/D/0/F/G/H/I]).
derecha([A/B/C/D/E/F/0/H/I]).
derecha([A/B/C/D/E/F/G/0/I]).

abajo([0/B/C/D/E/F/G/H/I]).
abajo([A/0/C/D/E/F/G/H/I]).
abajo([A/B/0/D/E/F/G/H/I]).
abajo([A/B/C/0/E/F/G/H/I]).
abajo([A/B/C/D/0/F/G/H/I]).
abajo([A/B/C/D/E/0/G/H/I]).


aplicar_movida([A/0/C/D/E/F/G/H/I],izquierda,[0/A/C/D/E/F/G/H/I]).
aplicar_movida([A/B/0/D/E/F/G/H/I],izquierda,[A/0/B/D/E/F/G/H/I]).
aplicar_movida([A/B/C/D/0/F/G/H/I],izquierda,[A/B/C/0/D/F/G/H/I]).
aplicar_movida([A/B/C/D/E/0/G/H/I],izquierda,[A/B/C/D/0/E/G/H/I]).
aplicar_movida([A/B/C/D/E/F/G/0/I],izquierda,[A/B/C/D/E/F/0/G/I]).
aplicar_movida([A/B/C/D/E/F/G/H/0],izquierda,[A/B/C/D/E/F/G/0/H]).

aplicar_movida([A/B/C/0/E/F/G/H/I],arriba,[0/B/C/A/E/F/G/H/I]).
aplicar_movida([A/B/C/D/0/F/G/H/I],arriba,[A/0/C/D/B/F/G/H/I]).
aplicar_movida([A/B/C/D/E/0/G/H/I],arriba,[A/B/0/D/E/C/G/H/I]).
aplicar_movida([A/B/C/D/E/F/0/H/I],arriba,[A/B/C/0/E/F/D/H/I]).
aplicar_movida([A/B/C/D/E/F/G/0/I],arriba,[A/B/C/D/0/F/G/E/I]).
aplicar_movida([A/B/C/D/E/F/G/H/0],arriba,[A/B/C/D/E/0/G/H/F]).

aplicar_movida([0/B/C/D/E/F/G/H/I],derecha,[B/0/C/D/E/F/G/H/I]).
aplicar_movida([A/0/C/D/E/F/G/H/I],derecha,[A/C/0/D/E/F/G/H/I]).
aplicar_movida([A/B/C/0/E/F/G/H/I],derecha,[A/B/C/E/0/F/G/H/I]).
aplicar_movida([A/B/C/D/0/F/G/H/I],derecha,[A/B/C/D/F/0/G/H/I]).
aplicar_movida([A/B/C/D/E/F/0/H/I],derecha,[ A/B/C/D/E/F/H/0/I]).
aplicar_movida([A/B/C/D/E/F/G/0/I],derecha,[A/B/C/D/E/F/G/I/0]).

aplicar_movida([0/B/C/D/E/F/G/H/I],abajo,[D/B/C/0/E/F/G/H/I]).
aplicar_movida([A/0/C/D/E/F/G/H/I],abajo,[A/E/C/D/0/F/G/H/I]).
aplicar_movida([A/B/0/D/E/F/G/H/I],abajo,[A/B/F/D/E/0/G/H/I]).
aplicar_movida([A/B/C/0/E/F/G/H/I],abajo,[A/B/C/G/E/F/0/H/I]).
aplicar_movida([A/B/C/D/0/F/G/H/I],abajo,[A/B/C/D/H/F/G/0/I]).
aplicar_movida([A/B/C/D/E/0/G/H/I],abajo,[A/B/C/D/E/I/G/H/0]).




















