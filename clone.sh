#!/usr/bin/env bash

function clone {
    echo "cloning "$1" "$2
    git remote add -f $1 $2
    git merge -s ours --no-commit --allow-unrelated-histories $1/master
    git read-tree --prefix=$1/ -u $1/main
    git commit -m "Merge project $1 as our subdirectory"
}

while read repo; do
    clone $repo
done <./repos
