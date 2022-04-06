package priv.hy.service;

import org.apache.ibatis.annotations.Param;
import priv.hy.pojo.Brand;
import priv.hy.pojo.PageBean;

import java.io.IOException;
import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll() throws IOException;

    /**
     * 添加
     * @param brand
     */
    void add(Brand brand) throws IOException;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Brand selectById(Integer id) throws IOException;

    /**
     * 修改
     * @param brand
     */
    void update(Brand brand) throws IOException;


    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id) throws IOException;

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(int[] ids) throws IOException;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Brand> selectByPage(Integer currentPage, Integer pageSize) throws IOException;

    /**
     * 多条件查询总记录数
     * @param brand
     * @param currentPage
     * @param pageSize
     * @return
     * @throws IOException
     */
    PageBean<Brand> selectByPageAndCondition(Integer currentPage, Integer pageSize ,Brand brand) throws IOException;


}
