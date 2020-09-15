# script-filedump
fileのメタデータ系を出力するコマンドを投下

javaはここを参考に実装
- https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html

Rubyはここを参考に実装
- https://github.com/kig/metadata

Perlはここを参考に実装
- https://stackoverrun.com/ja/q/155708

一般ユーザーで実行

bashrcにパスが追加されている

```
$ cpan install String::Random

$ perl -C -MString::Random -E 'for(keys %{String::Random::}){say "$_" if exists &{"String::Random::$_"};}'
confess
from_pattern
randpattern
randregex
_rand
random_regex
random_string
carp
new
croak
```
