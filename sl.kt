package com.aliucord.plugins;

import android.content.Context;
import android.text.TextUtils;
import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.api.CommandsAPI;
import com.aliucord.entities.Plugin;

import java.util.Random;

import com.discord.api.emoji.EmojiManager;
import com.discord.models.message.Message;

@AliucordPlugin
public class sl extends Plugin {
    private static final Random random = new Random();

    @Override
    public void start(Context context) {
        commands.registerCommand(
            "clap",
            "Clap your messages",
            listOf(CommandsAPI.requiredMessageOption), ctx -> {
                String msg = ctx.getRequiredString("message");
                String emoji = getRandomEmoji();
                String clap = addClapEmoji(msg, emoji);
                return new CommandsAPI.CommandResult(clap);
        });
    }

    @Override
    public void stop(Context context) {
        commands.unregisterAll();
    }
    
    private String getRandomEmoji() {
        String[] emojis = {"👏", "👍", "❤️", "😂", "🔥"}; // пример набора случайных эмодзи
        int index = random.nextInt(emojis.length);
        return emojis[index];
    }

    private String addClapEmoji(String msg, String emoji) {
        return emoji + " " + msg.trim();
    }
}