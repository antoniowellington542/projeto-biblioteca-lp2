#!/bin/bash

diretorio_raiz="$(pwd)/biblioteca-lp2"

# Função para excluir os arquivos .class
excluir_arquivos_class() {
    local diretorio="$1"
    # Percorre todos os arquivos e subpastas dentro do diretório
    for nome in "$diretorio"/*; do
        if [[ -f "$nome" ]]; then
            # Verifica se o arquivo é .class
            if [[ "$nome" == *.class ]]; then
                # Remove o arquivo .class
                rm "$nome"
            fi
        elif [[ -d "$nome" ]]; then
            # Chama recursivamente a função para a subpasta
            excluir_arquivos_class "$nome"
        fi
    done
}

# Chama a função para excluir os arquivos .class dentro do diretório raiz
excluir_arquivos_class "$diretorio_raiz"
