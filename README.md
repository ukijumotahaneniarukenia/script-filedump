# script-filedump
fileのメタデータ系を出力するコマンドを投下

javaはここを参考に実装
- https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html

javascriptはここを参考に実装
- https://qiita.com/suin/items/b807769388c54c57a8be
メソッドなども全てプロパティ管理されているので、
シグニチャのif文で抽出対象を特定して文字列配列で取得する

オブジェクト化のやり方

Rubyはここを参考に実装
- https://github.com/kig/metadata

Pythonはここを参考に実装

- https://docs.python.org/3/library/importlib.metadata.html

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

$ perl -C -MString::Random -E 'map {say $_} keys %{String::Random::}'
VERSION
randregex
confess
ISA
random_string
BEGIN
random_regex
randpattern
import
EXPORT_TAGS
_rand
EXPORT_OK
from_pattern
carp
__ANON__
EXPORT
new
croak

$ perl -C -M'String::Random' -M'Class::Inspector qw/filename/' -E 'say Class::Inspector->filename("String::Random");'
String/Random.pm

$ find / -type f 2>/dev/null | grep 'String/Random.pm'
/home/aine/.cpan/build/String-Random-0.30-0/blib/lib/String/Random.pm
/home/aine/.cpan/build/String-Random-0.30-0/lib/String/Random.pm
/home/aine/.local/share/.cpan/build/String-Random-0.30-0/blib/lib/String/Random.pm
/home/aine/.local/share/.cpan/build/String-Random-0.30-0/lib/String/Random.pm
/home/aine/perl5/lib/perl5/String/Random.pm
/usr/local/share/perl/5.26.1/String/Random.pm

$ perl -C -M'String::Random' -M'Class::Inspector' -E 'map{say \@{$_}}Class::Inspector->functions("String::Random");'
ARRAY(0x558d01ec15d8)

$ perl -C -M'String::Random' -M'Class::Inspector' -E 'map{map{say }@{$_}}Class::Inspector->functions("String::Random");'
_rand
carp
confess
croak
from_pattern
new
random_regex
random_string
randpattern
randregex

```
