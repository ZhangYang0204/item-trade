package pers.zhangyang.itemtrade.base;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import pers.zhangyang.itemtrade.ItemTrade;
import pers.zhangyang.itemtrade.yaml.SettingYaml;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public abstract class YamlManagerBase {
    protected YamlConfiguration yamlConfiguration;
    protected String filePath;
    protected YamlConfiguration backUpConfiguration;


    /**
     *
     * @param filePath 在resource下的文件路径
     * @throws IOException
     * @throws InvalidConfigurationException
     */

    protected YamlManagerBase(String filePath){
        this.filePath=filePath;
        this.yamlConfiguration=new YamlConfiguration();
        this.backUpConfiguration=new YamlConfiguration();
    }

    /**
     * 会把对应的文件保存到Pluginitemtrade下
     * @throws IOException
     * @throws InvalidConfigurationException
     */
    public void init( ) throws IOException, InvalidConfigurationException {

        File file=new File(ItemTrade.getInstance().getDataFolder(), filePath);
        //如果文件不存在就创建
        if (!file.exists()){
            File dir=file.getParentFile();
            //先创建目录文件夹
            if (!dir.exists()){dir.mkdirs();}
            //输出数据
            InputStream in= SettingYaml.class.getClassLoader().getResourceAsStream(filePath);
            OutputStream out = Files.newOutputStream(file.toPath());
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0) {out.write(buf, 0, len);}
            out.close();
            in.close();
        }
        //加载Yaml
        this.yamlConfiguration.load(file);

        InputStreamReader inputStreamReader=new InputStreamReader(YamlManagerBase.class.getClassLoader()
                .getResourceAsStream(filePath), StandardCharsets.UTF_8);
        this.backUpConfiguration.load(inputStreamReader);
        check();
    }

    /**
     * 用于检查配置文件的值的正确性
     * @throws IOException
     * @throws InvalidConfigurationException
     */
    protected abstract void check() throws IOException, InvalidConfigurationException;

    /**
     * 修正配置文件内容
     * @throws IOException
     */
    public void correct() throws IOException {
        //删除多余的
        for (String path: yamlConfiguration.getKeys(true)){
            if (!backUpConfiguration.getKeys(true).contains(path)){
                Object ob=yamlConfiguration.get(path);
                yamlConfiguration.set(path,null);
                try {
                    yamlConfiguration.save(ItemTrade.getInstance().getDataFolder().getAbsoluteFile()+"/"+filePath);
                } catch (IOException e) {
                    yamlConfiguration.set(path,ob);
                    throw e;
                }
            }

        }
        //补充缺失的
        for (String pathBase:backUpConfiguration.getKeys(true)){
            if (!yamlConfiguration.getKeys(true).contains(pathBase)){
                Object ob=yamlConfiguration.get(pathBase);
                yamlConfiguration.set(pathBase,backUpConfiguration.get(pathBase));
                try {
                    yamlConfiguration.save(ItemTrade.getInstance().getDataFolder().getAbsoluteFile()+"/"+filePath);

                } catch (IOException e) {
                    yamlConfiguration.set(pathBase,ob);
                    throw e;
                }

            }
        }
    }


    /**
     * 将指定键设置为resource里的默认值
     * @param path
     */
    private     void setDefault( @Nonnull String path) {

        Object ob=yamlConfiguration.get(path);
        yamlConfiguration.set(path,backUpConfiguration.get(path));
        try {
            yamlConfiguration.save(ItemTrade.getInstance().getDataFolder().getAbsoluteFile()+"/"+filePath);
        } catch (IOException e) {
            yamlConfiguration.set(path,ob);
        }

    }
    @Nullable
    public Boolean getBoolean(@Nonnull String path ,boolean isDef){
        if (!yamlConfiguration.isBoolean(path)){
            if (!isDef){
                return null;
            }
            setDefault(path);
        }
        return yamlConfiguration.getBoolean(path);
    }
    @Nullable
    public String getString(@Nonnull String path,boolean isDef){
        if (!yamlConfiguration.isString(path)) {
            if (!isDef){
                return null;
            }
            setDefault(path);
        }
        return yamlConfiguration.getString(path);
    }
    @Nullable
    public Integer getInteger(@Nonnull String path, boolean isDef){
        if (!yamlConfiguration.isInt(path)) {
            if (!isDef){
                return null;
            }
            setDefault(path);
        }
        return  yamlConfiguration.getInt(path);
    }

    @Nullable
    public Long getLong(@Nonnull String path,boolean isDef){
        if (!yamlConfiguration.isLong(path)) {
            if (!isDef){
                return null;
            }
            setDefault(path);
        }
        return  yamlConfiguration.getLong(path);
    }

    @Nullable
    public Double getDouble(@Nonnull String path,boolean isDef){
        if (!yamlConfiguration.isDouble(path)) {
            if (!isDef){
                return null;
            }
            setDefault(path);
        }
        return  yamlConfiguration.getDouble(path);
    }

    @Nullable
    public List<String> getStringList(@Nonnull String path, boolean isDef){
        if (!yamlConfiguration.isList(path)){
            if (!isDef){
                return null;
            }
            setDefault(path);
        }
        return  yamlConfiguration.getStringList(path);
    }

}
