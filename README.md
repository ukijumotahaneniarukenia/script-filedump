# script-filedump
fileのメタデータ系を出力するコマンドを投下

javaはここを参考に実装
- https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html

javascriptはここを参考に実装
- https://qiita.com/suin/items/b807769388c54c57a8be
メソッドなども全てプロパティ管理されているので、
シグニチャのif文で抽出対象を特定して文字列配列で取得する

- https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Global_Objects/eval

evalで動的に実行する
リフレクションの代わり

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

$ perl -C -M'String::Random' -M'Class::Inspector' -E 'map{map{say }@{$_}}Class::Inspector->methods("String::Random","public");'
as_heavy
carp
confess
croak
export
export_fail
export_ok_tags
export_tags
export_to_level
from_pattern
import
new
random_regex
random_string
randpattern
randregex
require_version


$ perl -C -M'String::Random' -M'Class::Inspector' -E 'map{map{say }@{$_}}Class::Inspector->methods("String::Random","private");'
_rand

$ perl -C -M'String::Random' -M'Class::Inspector' -E 'map{map{say }@{$_}}Class::Inspector->methods("String::Random","full");'
String::Random::_rand
Exporter::as_heavy
String::Random::carp
String::Random::confess
String::Random::croak
Exporter::export
Exporter::export_fail
Exporter::export_ok_tags
Exporter::export_tags
Exporter::export_to_level
String::Random::from_pattern
Exporter::import
String::Random::new
String::Random::random_regex
String::Random::random_string
String::Random::randpattern
String::Random::randregex
Exporter::require_version


$ perl -C -M'String::Random' -M'Class::Inspector' -E 'map{map{say }@{$_}}Class::Inspector->subclasses("String::Random");'


$ perl -C -M'String::Random' -M'Class::Inspector' -E 'map{map{map{say $_}@{$_}}@{$_}}Class::Inspector->methods("String::Random","expanded");'
String::Random::_rand
String::Random
_rand
CODE(0x5583fd401728)
Exporter::as_heavy
Exporter
as_heavy
CODE(0x5583fd34b450)
String::Random::carp
String::Random
carp
CODE(0x5583fd33d0a0)
String::Random::confess
String::Random
confess
CODE(0x5583fd33d010)
String::Random::croak
String::Random
croak
CODE(0x5583fd33cf98)
Exporter::export
Exporter
export
CODE(0x5583fd3c66e8)
Exporter::export_fail
Exporter
export_fail
CODE(0x5583fd3f29c0)
Exporter::export_ok_tags
Exporter
export_ok_tags
CODE(0x5583fd3f2c78)
Exporter::export_tags
Exporter
export_tags
CODE(0x5583fd3f2be8)
Exporter::export_to_level
Exporter
export_to_level
CODE(0x5583fd3da100)
String::Random::from_pattern
String::Random
from_pattern
CODE(0x5583fd41a198)
Exporter::import
Exporter
import
CODE(0x5583fd3c6b98)
String::Random::new
String::Random
new
CODE(0x5583fd419cb8)
String::Random::random_regex
String::Random
random_regex
CODE(0x5583fd41a660)
String::Random::random_string
String::Random
random_string
CODE(0x5583fd41a840)
String::Random::randpattern
String::Random
randpattern
CODE(0x5583fd41a408)
String::Random::randregex
String::Random
randregex
CODE(0x5583fd419f58)
Exporter::require_version
Exporter
require_version
CODE(0x5583fd3f2d08)

```
