#!/usr/bin/env bash

function pull {
    git pull -s subtree $1 master
}

while read repo; do
    pull $repo
done <./repos
