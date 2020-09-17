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


このファイルとか使えそう
/home/aine/.local/share/.cpan/build/String-Random-0.30-0/META.json
```

こちらのほうがより詳細に出力されそう

```
$ cpan install Class::MOP::Class

$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::Inspector' -E 'map{map{say }@{$_}}Class::Inspector->methods("Class::MOP","full");'
Class::MOP::IS_RUNNING_ON_5_10
Class::MOP::_definition_context
Class::MOP::blessed
Class::MOP::check_package_cache_flag
Class::MOP::class_of
Class::MOP::does_metaclass_exist
Class::MOP::get_all_metaclass_instances
Class::MOP::get_all_metaclass_names
Class::MOP::get_all_metaclasses
Class::MOP::get_code_info
Class::MOP::get_metaclass_by_name
Class::MOP::is_class_loaded
Class::MOP::isweak
Class::MOP::load_class
Class::MOP::load_first_existing_class
Class::MOP::metaclass_is_weak
Class::MOP::remove_metaclass_by_name
Class::MOP::store_metaclass_by_name
Class::MOP::weaken
Class::MOP::weaken_metaclass

$ cpan install Class::MOP::Class

$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::Inspector' -E 'map{map{say }@{$_}}Class::Inspector->methods("Class::MOP","full");'
Class::MOP::IS_RUNNING_ON_5_10
Class::MOP::_definition_context
Class::MOP::blessed
Class::MOP::check_package_cache_flag
Class::MOP::class_of
Class::MOP::does_metaclass_exist
Class::MOP::get_all_metaclass_instances
Class::MOP::get_all_metaclass_names
Class::MOP::get_all_metaclasses
Class::MOP::get_code_info
Class::MOP::get_metaclass_by_name
Class::MOP::is_class_loaded
Class::MOP::isweak
Class::MOP::load_class
Class::MOP::load_first_existing_class
Class::MOP::metaclass_is_weak
Class::MOP::remove_metaclass_by_name
Class::MOP::store_metaclass_by_name
Class::MOP::weaken
Class::MOP::weaken_metaclass

$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::Inspector' -E 'map{say $_}sort Class::MOP->get_all_metaclass_names("Class::MOP");'
Class::MOP::Attribute
Class::MOP::Class
Class::MOP::Class::Immutable::Class::MOP::Class
Class::MOP::Class::Immutable::Trait
Class::MOP::Instance
Class::MOP::Method
Class::MOP::Method::Accessor
Class::MOP::Method::Constructor
Class::MOP::Method::Generated
Class::MOP::Method::Inlined
Class::MOP::Method::Meta
Class::MOP::Method::Wrapped
Class::MOP::Mixin
Class::MOP::Mixin::AttributeCore
Class::MOP::Mixin::HasAttributes
Class::MOP::Mixin::HasMethods
Class::MOP::Mixin::HasOverloads
Class::MOP::Module
Class::MOP::Object
Class::MOP::Overload
Class::MOP::Package
UNIVERSAL


$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::Inspector' -E 'map{say $_}sort Class::MOP->get_all_metaclass_names();'
Class::MOP::Attribute
Class::MOP::Class
Class::MOP::Class::Immutable::Class::MOP::Class
Class::MOP::Class::Immutable::Trait
Class::MOP::Instance
Class::MOP::Method
Class::MOP::Method::Accessor
Class::MOP::Method::Constructor
Class::MOP::Method::Generated
Class::MOP::Method::Inlined
Class::MOP::Method::Meta
Class::MOP::Method::Wrapped
Class::MOP::Mixin
Class::MOP::Mixin::AttributeCore
Class::MOP::Mixin::HasAttributes
Class::MOP::Mixin::HasMethods
Class::MOP::Mixin::HasOverloads
Class::MOP::Module
Class::MOP::Object
Class::MOP::Overload
Class::MOP::Package
UNIVERSAL


$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::Inspector' -E 'map{say $_}sort Class::MOP->get_all_metaclass_instances();'
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x5573353b8270)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x5573353cb748)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x5573357cc7c0)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x55733583da60)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335841c88)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x5573358423a8)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335842858)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x5573358468f0)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335848ca8)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335848f90)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335859700)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335860750)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335862728)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x5573358629f8)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335862a28)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335865a10)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335865a28)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x55733586f928)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x557335876f10)
Class::MOP::Class::Immutable::Class::MOP::Class=HASH(0x55733588e460)
Class::MOP::Class=HASH(0x5573358847c8)
Class::MOP::Class=HASH(0x557335884d68)


$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::MOP::Class' -M'Class::Inspector' -E 'map{say $_}sort Class::MOP->get_all_metaclass_names("Class::MOP::Method");'
Class::MOP::Attribute
Class::MOP::Class
Class::MOP::Class::Immutable::Class::MOP::Class
Class::MOP::Class::Immutable::Trait
Class::MOP::Instance
Class::MOP::Method
Class::MOP::Method::Accessor
Class::MOP::Method::Constructor
Class::MOP::Method::Generated
Class::MOP::Method::Inlined
Class::MOP::Method::Meta
Class::MOP::Method::Wrapped
Class::MOP::Mixin
Class::MOP::Mixin::AttributeCore
Class::MOP::Mixin::HasAttributes
Class::MOP::Mixin::HasMethods
Class::MOP::Mixin::HasOverloads
Class::MOP::Module
Class::MOP::Object
Class::MOP::Overload
Class::MOP::Package
UNIVERSAL

$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::MOP::Class' -M'Class::Inspector' -E 'my $meta = Class::MOP::Class->initialize("String::Random");map{say}sort $meta->get_method_list();'
_rand
from_pattern
new
random_regex
random_string
randpattern
randregex

$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::MOP::Class' -M'Class::Inspector' -E 'my $meta = Class::MOP::Class->initialize("String::Random");map{say}sort $meta->get_all_methods();'
Class::MOP::Method=HASH(0x55d89c592610)
Class::MOP::Method=HASH(0x55d89c5926b8)
Class::MOP::Method=HASH(0x55d89c592760)
Class::MOP::Method=HASH(0x55d89c596368)
Class::MOP::Method=HASH(0x55d89c5965a8)
Class::MOP::Method=HASH(0x55d89c596740)
Class::MOP::Method=HASH(0x55d89c600a60)
Class::MOP::Method=HASH(0x55d89c600b08)
Class::MOP::Method=HASH(0x55d89c600bc8)
Class::MOP::Method=HASH(0x55d89c600c70)
Class::MOP::Method=HASH(0x55d89c66ba38)
Class::MOP::Method=HASH(0x55d89c6a2ec8)
Class::MOP::Method=HASH(0x55d89c6a2f70)
Class::MOP::Method=HASH(0x55d89c6a3018)
Class::MOP::Method=HASH(0x55d89c6a30c0)
Class::MOP::Method=HASH(0x55d89c6a3168)
Class::MOP::Method=HASH(0x55d89c6a3210)
Class::MOP::Method=HASH(0x55d89c6a32d0)
Class::MOP::Method=HASH(0x55d89c6a3378)

$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::MOP::Class' -M'Class::Inspector' -E 'my $meta = Class::MOP::Class->initialize("String::Random");map{say $_}$meta->get_attribute_list();' 


$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::MOP::Class' -M'Class::Inspector' -E 'my $meta = Class::MOP::Class->initialize("String::Random");map{say $_}$meta->get_all_attributes();' 

$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::MOP::Class' -M'Class::Inspector' -E 'my $meta = Class::MOP::Class->initialize("Class::MOP::Class");map{say $_}$meta->get_attribute_list();'
instance_metaclass
destructor_class
constructor_name
superclasses
immutable_trait
constructor_class


$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::MOP::Class' -M'Class::Inspector' -E 'my $meta = Class::MOP::Class->initialize("Class::MOP::Class");map{say $_}$meta->get_all_attributes();'
Class::MOP::Attribute=HASH(0x5592be808488)
Class::MOP::Attribute=HASH(0x5592be803c10)
Class::MOP::Attribute=HASH(0x5592be7fd9a0)
Class::MOP::Attribute=HASH(0x5592be374c68)
Class::MOP::Attribute=HASH(0x5592be804630)
Class::MOP::Attribute=HASH(0x5592be807e70)
Class::MOP::Attribute=HASH(0x5592be7fd220)
Class::MOP::Attribute=HASH(0x5592be7f9b68)
Class::MOP::Attribute=HASH(0x5592be803fa0)
Class::MOP::Attribute=HASH(0x5592be801a28)
Class::MOP::Attribute=HASH(0x5592be7f8f38)
Class::MOP::Attribute=HASH(0x5592be8086e0)
Class::MOP::Attribute=HASH(0x5592be808080)
Class::MOP::Attribute=HASH(0x5592be801020)
Class::MOP::Attribute=HASH(0x5592be7f9490)
Class::MOP::Attribute=HASH(0x5592be8079a8)



$ perl -C -M'String::Random' -M'Class::MOP' -M'Class::MOP::Class' -M'Class::Inspector' -E 'my $meta = Class::MOP::Class->initialize("Class::MOP::Class");map{say $_}sort $meta->get_all_method_names();'
DESTROY
DOES
VERSION
_SET_FALLBACK_EACH_TIME
_add_inlined_method
_add_meta_method
_anon_cache_key
_anon_package_prefix
_attach_attribute
_attribute_map
_base_metaclasses
_can_be_made_compatible_with
_can_fix_metaclass_incompatibility
_check_class_metaclass_compatibility
_check_metaclass_compatibility
_check_single_metaclass_compatibility
_class_metaclass_can_be_made_compatible
_class_metaclass_is_compatible
_clear_overload_map
_clone_instance
_code_is_mine
_construct_class_instance
_construct_instance
_create_meta_instance
_eval_environment
_fix_class_metaclass_incompatibility
_fix_metaclass_incompatibility
_fix_single_metaclass_incompatibility
_fixup_attributes_after_rebless
_force_rebless_instance
_free_anon
_full_method_map
_generate_fallback_constructor
_get_compatible_metaclass
_get_compatible_metaclass_by_subclassing
_get_local_methods
_get_maybe_raw_method
_immutable_metaclass
_immutable_options
_initialize_immutable
_inline_accessors
_inline_clear_mop_slot
_inline_constructor
_inline_create_instance
_inline_default_value
_inline_destructor
_inline_extra_init
_inline_fallback_constructor
_inline_generate_instance
_inline_get_mop_slot
_inline_init_attr_from_constructor
_inline_init_attr_from_default
_inline_new_object
_inline_params
_inline_preserve_weak_metaclasses
_inline_rebless_instance
_inline_set_mop_slot
_inline_slot_initializer
_inline_slot_initializers
_inline_throw_error
_inlined_methods
_install_inlined_code
_instantiate_module
_is_compatible_with
_make_compatible_with
_meta_method_class
_method_lookup_order
_method_map
_new
_overload_for
_overload_info
_overload_info_for
_overload_map
_package_stash
_post_add_attribute
_real_ref_name
_rebless_as_immutable
_rebless_as_mutable
_remove_generated_metaobjects
_remove_inlined_code
_restore_metaattributes_from
_restore_metamethods_from
_restore_metaobjects_from
_single_metaclass_can_be_made_compatible
_single_metaclass_is_compatible
_superclass_metas
_superclasses_updated
_throw_exception
add_after_method_modifier
add_around_method_modifier
add_attribute
add_before_method_modifier
add_dependent_meta_instance
add_meta_instance_dependencies
add_method
add_overloaded_operator
add_package_symbol
attribute_metaclass
authority
can
class_precedence_list
clone_object
constructor_class
constructor_name
create
create_anon
create_anon_class
destructor_class
direct_subclasses
dump
find_all_methods_by_name
find_attribute_by_name
find_method_by_name
find_next_method_by_name
get_all_attributes
get_all_method_names
get_all_methods
get_all_overloaded_operators
get_all_package_symbols
get_attribute
get_attribute_list
get_meta_instance
get_method
get_method_list
get_or_add_package_symbol
get_overload_fallback_value
get_overload_list
get_overloaded_operator
get_package_symbol
has_attribute
has_method
has_overloaded_operator
has_package_symbol
identifier
immutable_options
immutable_trait
initialize
instance_metaclass
invalidate_meta_instance
invalidate_meta_instances
is_anon
is_anon_class
is_immutable
is_mutable
is_overloaded
is_pristine
isa
linearized_isa
list_all_package_symbols
make_immutable
make_mutable
meta
method_metaclass
name
namespace
new_object
rebless_instance
rebless_instance_away
rebless_instance_back
reinitialize
remove_attribute
remove_dependent_meta_instance
remove_meta_instance_dependencies
remove_method
remove_overloaded_operator
remove_package_glob
remove_package_symbol
reset_package_cache_flag
set_overload_fallback_value
subclasses
superclasses
throw_error
update_meta_instance_dependencies
update_package_cache_flag
version
wrap_method_body
wrapped_method_metaclass

```
