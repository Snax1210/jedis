package org.shiqiu.jedis.service;

import redis.clients.jedis.BinaryClient;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
    /**
     * 对象封装成一个<String,String>的map
     *
     * @param object 对象
     * @return Map<String, String>
     */
    Map<String, String> getProperty(Object object);

    /**
     * 获取指定key的值,如果key不存在返回null，如果该Key存储的不是字符串，会抛出一个错误
     *
     * @param key 键
     * @return 查找出的String类型的值
     */
    String getString(String key);

    /**
     * 设置key的值为value
     *
     * @param key   String 键
     * @param value String 值
     * @return String
     */
    String setString(String key, String value);

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param keys 可变String类型的键
     * @return Long
     */
    Long del(String... keys);

    /**
     * 通过key向指定的value值追加值
     *
     * @param key         键
     * @param appendValue 追加值
     * @return Long
     */
    Long appendString(String key, String appendValue);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return 存在为true 不存在为false
     */
    Boolean exists(String key);

    /**
     * 设置key value,如果key已经存在则返回0
     *
     * @param key   键
     * @param value 值
     * @return Long
     */
    Long setNx(String key, String value);

    /**
     * 设置key value并指定这个键值的有效期
     *
     * @param key     键
     * @param seconds 超时时间
     * @param value   值
     * @return String
     */
    String setEx(String key, int seconds, String value);

    /**
     * 通过key 和offset 从指定的位置开始将原先value替换
     *
     * @param key    键
     * @param offset 指定位置索引
     * @param str    替换值
     * @return Long
     */
    Long setRange(String key, int offset, String str);

    /**
     * 通过批量的key获取批量的value
     *
     * @param keys 键
     * @return List
     */
    List<String> mGet(String... keys);

    /**
     * 批量的设置key:value,也可以一个
     *
     * @param keysValues 可变key
     * @return String
     */
    String mSet(String... keysValues);

    /**
     * 批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚
     *
     * @param keysValues 可变keyvalue
     * @return Long
     */
    Long mSetNx(String... keysValues);

    /**
     * 设置key的值,并返回一个旧值
     *
     * @param key   键
     * @param value 覆盖的值
     * @return String 旧值
     */
    String getSet(String key, String value);

    /**
     * 通过下标 和key 获取指定下标位置的 value
     *
     * @param key         键
     * @param startOffset 开始下标
     * @param endOffset   结束下标
     * @return String
     */
    String getRange(String key, int startOffset, int endOffset);

    /**
     * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
     *
     * @param key 键
     * @return Long
     */
    Long incr(String key);

    /**
     * 通过key给指定的value加值,如果key不存在,则这是value为该值
     *
     * @param key     键
     * @param integer 加值
     * @return Long
     */
    Long incrBy(String key, long integer);

    /**
     * 对key的值做减减操作,如果key不存在,则设置key为-1
     *
     * @param key 键
     * @return Long
     */
    Long decr(String key);

    /**
     * 减去指定的值
     *
     * @param key     键
     * @param integer 值
     * @return Long
     */
    Long decrBy(String key, long integer);

    /**
     * 通过key获取value值的长度
     *
     * @param key 键
     * @return Long 长度
     */
    Long strLen(String key);

    /**
     * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0
     *
     * @param key   键
     * @param field 属性
     * @param value 值
     * @return Long
     */
    Long hSetNx(String key, String field, String value);

    /**
     * 通过key给field设置指定的值,如果key不存在,则先创建
     *
     * @param key   键
     * @param field 属性
     * @param value 值
     * @return Long
     */
    Long hSet(String key, String field, String value);

    /**
     * 通过key同时设置 hash的多个field
     *
     * @param key  键
     * @param hash map
     * @return String
     */
    String hmSet(String key, Map<String, String> hash);

    /**
     * 通过key 和 field 获取指定的 value
     *
     * @param key   键
     * @param field 属性
     * @return String 属性值
     */
    String hGet(String key, String field);

    /**
     * 设置key的超时时间为seconds
     *
     * @param key     键
     * @param seconds 超时时间
     * @return Long
     */
    Long expire(String key, int seconds);

    /**
     * 通过key 和 fields 获取指定的value 如果没有对应的value则返回null
     *
     * @param key    键
     * @param fields 可以是 一个String 也可以是 String数组
     * @return List
     */
    List<String> hmGet(String key, String... fields);

    /**
     * 通过key给指定的field的value加上给定的值
     *
     * @param key   键
     * @param field 属性
     * @param value 值
     * @return Long
     */
    Long hIncrBy(String key, String field, Long value);

    /**
     * 通过key和field判断是否有指定的value存在
     *
     * @param key   键
     * @param field 属性
     * @return Boolean
     */
    Boolean hExists(String key, String field);

    /**
     * 通过key返回field的数量
     *
     * @param key 键
     * @return Long
     */
    Long hLen(String key);

    /**
     * 通过key 删除指定的 field
     *
     * @param key    键
     * @param fields 可以是 一个 field 也可以是 一个数组
     * @return Long
     */
    Long hDel(String key, String... fields);

    /**
     * 通过key返回所有的field
     *
     * @param key 键
     * @return Set
     */
    Set<String> hKeys(String key);

    /**
     * 通过key返回所有和key有关的value
     *
     * @param key 键
     * @return List
     */
    List<String> hVals(String key);

    /**
     * 通过key获取所有的field和value
     *
     * @param key 键
     * @return Map
     */
    Map<String, String> hGetAll(String key);

    /**
     * 通过key向list头部添加字符串
     *
     * @param key     键
     * @param strings 可以是一个string 也可以是string数组
     * @return 返回list的value个数
     */
    Long lPush(String key, String... strings);

    /**
     * 通过key向list尾部添加字符串
     *
     * @param key     键
     * @param strings 可以是一个string 也可以是string数组
     * @return 返回list的value个数
     */
    Long rPush(String key, String... strings);

    /**
     * 通过key在list指定的位置之前或者之后 添加字符串元素
     *
     * @param key   键
     * @param where LIST_POSITION枚举类型
     * @param pivot list里面的value
     * @param value 添加的value
     * @return Long
     */
    Long lInsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value);

    /**
     * 通过key设置list指定下标位置的value
     * 如果下标超过list里面value的个数则报错
     *
     * @param key   键
     * @param index 从0开始
     * @param value 值
     * @return 成功返回OK
     */
    String lSet(String key, Long index, String value);

    /**
     * 通过key从对应的list中删除指定的count个 和 value相同的元素
     *
     * @param key   键
     * @param count 当count为0时删除全部
     * @param value 值
     * @return 返回被删除的个数
     */
    Long lRem(String key, long count, String value);

    /**
     * 通过key保留list中从strat下标开始到end下标结束的value值
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return 成功返回OK
     */
    String lTrim(String key, long start, long end);

    /**
     * 通过key从list的头部删除一个value,并返回该value
     *
     * @param key 键
     * @return 值
     */
    String lPop(String key);

    /**
     * 通过key从list尾部删除一个value,并返回该元素
     *
     * @param key 键
     * @return 值
     */
    String rPop(String key);

    /**
     * 通过key从一个list的尾部删除一个value并添加到另一个list的头部,并返回该value
     * 如果第一个list为空或者不存在则返回null
     *
     * @param srcKey String
     * @param dstKey String
     * @return String
     */
    String rpoplpush(String srcKey, String dstKey);

    /**
     * 通过key获取list中指定下标位置的value
     *
     * @param key   键
     * @param index 下标
     * @return 如果没有返回null
     */
    String lIndex(String key, long index);

    /**
     * 通过key返回list的长度
     *
     * @param key 键
     * @return 长度
     */
    Long lLen(String key);

    /**
     * 通过key获取list指定下标位置的value
     * 如果start 为 0 end 为 -1 则返回全部的list中的value
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return List
     */
    List<String> lRange(String key, long start, long end);

    /**
     * 通过key向指定的set中添加value
     *
     * @param key     键
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    Long sAdd(String key, String... members);

    /**
     * 通过key删除set中对应的value值
     *
     * @param key     键
     * @param members 可以是一个String 也可以是一个String数组
     * @return 删除的个数
     */
    Long sRem(String key, String... members);

    /**
     * 通过key随机删除一个set中的value并返回该值
     *
     * @param key 键
     * @return 值
     */
    String sPop(String key);

    /**
     * 通过key获取set中的差集
     * 以第一个set为标准
     *
     * @param keys 可以 是一个string 则返回set中所有的value 也可以是string数组
     * @return Set
     */
    Set<String> sDiff(String... keys);

    /**
     * 通过key获取set中的差集并存入到另一个key中
     * 以第一个set为标准
     *
     * @param dstKey 差集存入的key
     * @param keys   可以 是一个string 则返回set中所有的value 也可以是string数组
     * @return Long
     */
    Long sDiffStore(String dstKey, String... keys);

    /**
     * 通过key获取指定set中的交集
     *
     * @param keys 可以 是一个string 也可以是一个string数组
     * @return Set
     */
    Set<String> sInter(String... keys);

    /**
     * 通过key获取指定set中的交集 并将结果存入新的set中
     *
     * @param dstKey String
     * @param keys   可以是一个string 也可以是一个string数组
     * @return Long
     */
    Long sInterStore(String dstKey, String... keys);

    /**
     * 通过key返回所有set的并集
     *
     * @param keys 可以 是一个string 也可以是一个string数组
     * @return Set
     */
    Set<String> sUnion(String... keys);

    /**
     * 通过key返回所有set的并集,并存入到新的set中
     *
     * @param dstKey 添加的
     * @param keys   可以 是一个string 也可以是一个string数组
     * @return Long
     */
    Long sUnionStore(String dstKey, String... keys);

    /**
     * 通过key将set中的value移除并添加到第二个set中
     *
     * @param srcKey 需要移除的
     * @param dstKey 添加的
     * @param member set中的value
     * @return Long
     */
    Long smove(String srcKey, String dstKey, String member);

    /**
     * 通过key获取set中value的个数
     *
     * @param key 键
     * @return 个数
     */
    Long sCard(String key);

    /**
     * 通过key判断value是否是set中的元素
     *
     * @param key    键
     * @param member 元素
     * @return Boolean
     */
    Boolean sIsMember(String key, String member);

    /**
     * 通过key获取set中随机的value,不删除元素
     *
     * @param key 键
     * @return String
     */
    String sRandMember(String key);

    /**
     * 通过key获取set中所有的value
     *
     * @param key 键
     * @return Set
     */
    Set<String> sMembers(String key);

    /**
     * 通过key向zset中添加value,score,其中score就是用来排序的
     * 如果该value已经存在则根据score更新元素
     *
     * @param key    键
     * @param score  排序值
     * @param member 元素
     * @return Long
     */
    Long zAdd(String key, double score, String member);

    /**
     * 通过key删除在zset中指定的value
     *
     * @param key     键
     * @param members 可以 是一个string 也可以是一个string数组
     * @return Long
     */
    Long zRem(String key, String... members);

    /**
     * 通过key增加该zset中value的score的值
     *
     * @param key    键
     * @param score  排序值
     * @param member String
     * @return Double
     */
    Double zIncrBy(String key, double score, String member);

    /**
     * 通过key返回zSet中value的排名
     * 下标从小到大排序
     *
     * @param key    键
     * @param member value
     * @return Long
     */
    Long zRank(String key, String member);

    /**
     * 通过key返回zSet中value的排名
     * 下标从大到小排序
     *
     * @param key    键
     * @param member 值
     * @return Long
     */
    Long zRevRank(String key, String member);

    /**
     * 通过key将获取score从start到end中zSet的value
     * score从大到小排序
     * 当start为0 end为-1时返回全部
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return Set
     */
    Set<String> zRevRange(String key, long start, long end);

    /**
     * 通过key返回指定score内zSet中的value
     * @param key 键
     * @param max 范围上限
     * @param min 范围下限
     * @return Set
     */
    Set<String> zRangeByScore(String key, String max, String min);

    /**
     * 通过key返回指定score内zSet中的value
     * @param key 键
     * @param max 范围上限
     * @param min 范围下限
     * @return Set
     */
    Set<String> zRangeByScore(String key, Double min, Double max);



    /**
     * 通过key返回指定score内zSet中的value(score相同的元素逆序排列)
     *
     * @param key 键
     * @param max 范围上限
     * @param min 范围下限
     * @return Set
     */
    Set<String> zRevRangeByScore(String key, String max, String min);

    /**
     * 通过key返回指定score内zSet中的value(score相同的元素逆序排列)
     *
     * @param key 键
     * @param max 范围上限
     * @param min 范围下限
     * @return Set
     */
    Set<String> zRevRangeByScore(String key, double max, double min);

    /**
     * 返回指定区间内zset中value的数量
     *
     * @param key 键
     * @param min 范围下限
     * @param max 范围上限
     * @return Long
     */
    Long zCount(String key, String min, String max);

    /**
     * 通过key返回zSet中的value个数
     *
     * @param key 键
     * @return Long
     */
    Long zCard(String key);

    /**
     * 通过key获取zSet中value的score值
     *
     * @param key    键
     * @param member value
     * @return Double
     */
    Double zScore(String key, String member);

    /**
     * 通过key删除给定区间内的元素
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return Long
     */
    Long zRemRangeByRank(String key, long start, long end);

    /**
     * 通过key删除指定score内的元素
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return Long
     */
    Long zRemRangeByScore(String key, double start, double end);

    /**
     * 返回满足pattern表达式的所有key
     * keys(*)
     * 返回所有的key
     *
     * @param pattern 正则表达式
     * @return Set
     */
    Set<String> keys(String pattern);

    /**
     * 通过key判断值得类型
     *
     * @param key 键
     * @return String 类型
     */
    String type(String key);

    /**
     * 清除所有数据
     * @return String OK
     */
    String flushAll();
}
