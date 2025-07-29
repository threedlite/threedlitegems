#!/usr/bin/env python3

import os
import subprocess

# Android icon sizes needed
sizes = {
    'mdpi': 48,
    'hdpi': 72, 
    'xhdpi': 96,
    'xxhdpi': 144,
    'xxxhdpi': 192,
    'play_store': 512
}

# Base directory
base_dir = '/home/user/git2/threedlitegems/play-store-assets/icons'
svg_file = os.path.join(base_dir, 'app_icon.svg')

# Create PNG versions using ImageMagick if available
for density, size in sizes.items():
    output_file = os.path.join(base_dir, f'ic_launcher_{density}_{size}x{size}.png')
    try:
        cmd = ['convert', '-background', 'transparent', '-size', f'{size}x{size}', svg_file, output_file]
        subprocess.run(cmd, check=True, capture_output=True)
        print(f"Created {output_file}")
    except subprocess.CalledProcessError:
        print(f"Failed to create {output_file} - ImageMagick not available")
    except FileNotFoundError:
        print("ImageMagick 'convert' command not found")
        break

print("Icon generation complete!")