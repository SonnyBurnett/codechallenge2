echo "cloning TomL"
git remote add -f TomL https://github.com/TomL-dev/coding-challenge.git
git merge -s ours --no-commit --allow-unrelated-histories TomL/develop
git read-tree --prefix=TomL/ -u TomL/develop
git commit -m "Merge project TomL as our subdirectory"
