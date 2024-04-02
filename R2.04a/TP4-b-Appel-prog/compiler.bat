@echo off

rem variables d'environnement
for %%A in ("%~dp0..\sgdk134") Do set "GDK_WIN_TMP=%%~fA"
if "%GDK_WIN%"=="" set PATH=c:\Program Files\Java\jdk-11.0.8\bin\;%GDK_WIN_TMP%;%PATH%
set GDK_WIN=%GDK_WIN_TMP%
set GDK=%GDK_WIN_TMP:\=/%
set GDK_WIN_TMP=

rem nettoyage prealable
set OUT_FILE=%~dp0\out\src\raquette_deplacer.o
if exist %OUT_FILE% del %OUT_FILE%
set OUT_FILE=

rem compilation
%GDK_WIN%\bin\make -f %GDK_WIN%\makefile.gen

rem rom file dans le repertoire principal
set ROM_FILE=%~dp0\out\rom.bin
if exist %ROM_FILE% copy %ROM_FILE% %~dp0\tp4_b_rom.bin

rem attente d'une saisie clavier
set /p _vvvv=Appuyez sur une touche pour fermer
set _vvvv=