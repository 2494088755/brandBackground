package priv.hy.mapper;

import org.apache.ibatis.annotations.*;
import priv.hy.pojo.Brand;

import java.util.List;

public interface BrandMapper {

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();


    /**
     * 增加
     * @param brand
     */
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     *  根据查询
     * @param id
     * @return
     */
    @Select("select * from tb_brand where id=#{id}")
    @ResultMap("brandResultMap")
    Brand selectById(Integer id);

    /**
     * 修改
     * @param brand
     */
    @ResultMap("brandResultMap")
    void update(Brand brand);

    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from tb_brand where id=#{id}")
    void deleteById(Integer id);



    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询
     *
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    /**
     * 分页多条件查询
     * @param brand
     * @param begin
     * @param size
     * @return
     */
    List<Brand> selectByPageAndCondition( @Param("begin") Integer begin, @Param("size") Integer size,@Param("brand") Brand brand);

    /**
     * 多条件查询总记录数
     * @param brand
     * @return
     */
    int selectTotalCountByCondition(Brand brand);
}
