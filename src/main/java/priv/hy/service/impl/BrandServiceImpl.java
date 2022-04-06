package priv.hy.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import priv.hy.mapper.BrandMapper;
import priv.hy.pojo.Brand;
import priv.hy.pojo.PageBean;
import priv.hy.service.BrandService;
import priv.hy.util.SqlSessionFactoryUtil;

import java.io.IOException;
import java.util.List;

public class BrandServiceImpl implements BrandService {
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Brand> selectAll() throws IOException {
        sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = mapper.selectAll();

        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) throws IOException {
        sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public Brand selectById(Integer id) throws IOException {
        sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = mapper.selectById(id);

        sqlSession.close();

        return brand;
    }

    @Override
    public void update(Brand brand) throws IOException {
        sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteById(Integer id) throws IOException {
        sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteById(id);

        sqlSession.commit();

        sqlSession.close();
    }

    /**
     * 批量删除
     * @param ids
     * @throws IOException
     */
    @Override
    public void deleteByIds(int[] ids) throws IOException {
        sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(Integer currentPage, Integer pageSize) throws IOException {
        sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //开始索引
        int begin = (currentPage - 1) * pageSize;
        //展示条数
        int size = pageSize;

        List<Brand> rows = mapper.selectByPage(begin, size);

        //总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();

        return pageBean;


    }

    /**
     * 多条件查询
     * @param brand
     * @return
     * @throws IOException
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(Integer currentPage, Integer pageSize, Brand brand) throws IOException {
        sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //开始索引
        int begin = (currentPage - 1) * pageSize;
        //展示条数
        int size = pageSize;

        //处理brand条件
        String brandName = brand.getBrandName();


        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();

        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        System.out.println(brandName.length());
        System.out.println(companyName.length());

        //查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin,size,brand);


        System.out.println(rows);
        //总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();

        return pageBean;
    }


}
