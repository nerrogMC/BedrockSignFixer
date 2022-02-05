package net.nerrog.bedrocksignfixer.listener;

import net.nerrog.bedrocksignfixer.BedrockSignFixer;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.floodgate.util.DeviceOs;

public class BlockPlaceEvent implements Listener {

    @EventHandler
    public void OnPlayerBlockPlaceEvent(SignChangeEvent e){
        //BEプレイヤーか
        if (BedrockSignFixer.floodgateApi.isFloodgatePlayer(e.getPlayer().getUniqueId())){
            boolean isShow = false;

            //configのNSWとandroidのみ有効オプションの確認
            if (BedrockSignFixer.config.getBoolean("target_NSW_ANDROID")){
                //確認
                if ((BedrockSignFixer.floodgateApi.getPlayer(e.getPlayer().getUniqueId()).getDeviceOs() == DeviceOs.NX) || (BedrockSignFixer.floodgateApi.getPlayer(e.getPlayer().getUniqueId()).getDeviceOs() == DeviceOs.GOOGLE)) {
                    isShow = true;
                }
            }else {
                isShow = true;
            }

            if (isShow){
                //フォームの送信
                CustomForm form = CustomForm.builder()
                        .title(BedrockSignFixer.config.getString("form_title"))
                        .label(BedrockSignFixer.config.getString("form_label"))
                        .input(BedrockSignFixer.config.getString("form_line1"))
                        .input(BedrockSignFixer.config.getString("form_line2"))
                        .input(BedrockSignFixer.config.getString("form_line3"))
                        .input(BedrockSignFixer.config.getString("form_line4"))
                        .responseHandler(((customForm, s) -> {
                            CustomFormResponse response = customForm.parseResponse(s);
                            if (!response.isCorrect()){
                                return;
                            }
                            //看板の編集
                            Sign sign = (Sign) e.getBlock().getState();
                            for (int i = 0; i < 4; i++){
                                if (response.getInput(i+1) != null){
                                    sign.setLine(i, response.getInput(i+1));
                                }
                            }
                            sign.update();

                        }))
                        .build();
                BedrockSignFixer.floodgateApi.getPlayer(e.getPlayer().getUniqueId()).sendForm(form);
            }
        }
    }
}
