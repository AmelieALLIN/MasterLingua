git config --global user.name "Travis CI"
git config --global user.email "noreply+travis@fossasia.org"

git clone --quiet --branch=apk https://sahnounjouhaina:$GITHUB_API_KEY@github.com/AmelieALLIN/MasterLingua apk > /dev/null

cd apk
\cp -r $TRAVIS_BUILD_DIR/app/build/outputs/apk/*/**.apk .

git checkout --orphan temporary

git add --all .
git commit -am "[Auto] Update Test Apk ($(date +%Y-%m-%d.%H:%M:%S))"

git branch -D apk
git branch -m apk

git push origin apk --force --quiet > /dev/null
